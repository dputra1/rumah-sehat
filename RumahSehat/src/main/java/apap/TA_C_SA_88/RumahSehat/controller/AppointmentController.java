package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.*;
import apap.TA_C_SA_88.RumahSehat.repository.AppointmentDb;
import apap.TA_C_SA_88.RumahSehat.repository.TagihanDb;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")

    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("adminServiceImpl")

    @Autowired
    private AdminService adminService;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private AppointmentDb appointmentDb;

    @Autowired
    private TagihanDb tagihanDb;

    Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @GetMapping("/appointment")
    public String viewAllAppointment(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        if(dokterService.getDokterByUsername(username)!=null) {
            DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }
        else if(adminService.findByUsername(username)!=null) {
            AdminModel userLoggedIn = adminService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }

        List<AppointmentModel> listAppointment = appointmentService.viewAllAppointment();

        model.addAttribute("listAppointment", listAppointment);
        return "appointment-viewall";
    }

    @GetMapping("/appointment/{kode}")
    public String viewAppointmentDetail(@PathVariable String kode, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        if(dokterService.getDokterByUsername(username)!=null) {
            DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }
        else {
            AdminModel userLoggedIn = adminService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }
        AppointmentModel appointmentModel = appointmentService.getAppointmentByKode(kode);
        DokterModel dokterModel = appointmentModel.getDokter();
        PasienModel pasienModel = appointmentModel.getPasien();
        model.addAttribute("appointment", appointmentModel);
        model.addAttribute("dokter", dokterModel);
        model.addAttribute("pasien", pasienModel);
        model.addAttribute("appointmentService", appointmentService);
        return "appointment-detail";
    }

    @GetMapping("/appointment/updateStatus/{kode}")
    public String updateAppointmentStatus(@PathVariable String kode) {
        AppointmentModel appointmentModel = appointmentService.getAppointmentByKode(kode);
        ResepModel resep = appointmentModel.getResep();
        if(resep == null){
            appointmentModel.setIsDone(Boolean.TRUE);
            

            TagihanModel tagihanModel = new TagihanModel();
            tagihanModel.setAppointment(appointmentModel);
            tagihanModel.setJumlahTagihan(appointmentModel.getDokter().getTarif());
            tagihanModel.setTanggalTerbuat(LocalDateTime.now());
            tagihanModel.setIsPaid(false);
            tagihanModel = tagihanDb.save(tagihanModel);

            appointmentModel.setTagihan(tagihanModel);
            appointmentService.saveApp(appointmentModel);
        }else{
            if(resep.getIsDone() == true){
                appointmentModel.setIsDone(Boolean.TRUE);
                List<JumlahModel> listJumlah = resep.getListJumlah();
                Integer totalTarif = 0;

                totalTarif += appointmentModel.getDokter().getTarif();
                for(JumlahModel jumlah : listJumlah){
                    totalTarif += jumlah.getKuantitas()*jumlah.getObat().getHarga();
                }
            
                TagihanModel tagihanModel = new TagihanModel();
                tagihanModel.setAppointment(appointmentModel);
                tagihanModel.setJumlahTagihan(totalTarif);
                tagihanModel.setTanggalTerbuat(LocalDateTime.now());
                tagihanModel.setIsPaid(false);
                tagihanModel = tagihanDb.save(tagihanModel);

                appointmentModel.setTagihan(tagihanModel);
                appointmentService.saveApp(appointmentModel);
            }else{
                return "fail-selesai-appointment";
            }
            
        }

        return "redirect:/appointment/" + kode;
    }
}
