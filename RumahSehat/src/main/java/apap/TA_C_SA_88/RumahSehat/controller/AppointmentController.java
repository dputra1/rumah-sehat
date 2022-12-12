package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.*;
import apap.TA_C_SA_88.RumahSehat.repository.AppointmentDb;
import apap.TA_C_SA_88.RumahSehat.repository.TagihanDb;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentService;
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
    private AppointmentDb appointmentDb;

    @Autowired
    private TagihanDb tagihanDb;

    Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @GetMapping("/appointment")
    public String viewAllAppointment(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedIn = adminService.findByUsername(username);

        List<AppointmentModel> listAppointment = appointmentService.viewAllAppointment();

        model.addAttribute("listAppointment", listAppointment);
        model.addAttribute("user", userLoggedIn);
        return "appointment-viewall";
    }

    @GetMapping("/appointment/{kode}")
    public String viewAllAppointment(@PathVariable String kode, Model model) {
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        AppointmentModel appointmentModel = appointmentService.getAppointmentByKode(kode);
        DokterModel dokterModel = appointmentModel.getDokter();
        PasienModel pasienModel = appointmentModel.getPasien();
        model.addAttribute("appointment", appointmentModel);
        model.addAttribute("dokter", dokterModel);
        model.addAttribute("pasien", pasienModel);
        model.addAttribute("user", userLoggedIn);
        model.addAttribute("appointmentService", appointmentService);
        return "appointment-detail";
    }

    @GetMapping("/appointment/updateStatus/{kode}")
    public String updateAppointmentStatus(@PathVariable String kode) {
        AppointmentModel appointmentModel = appointmentService.getAppointmentByKode(kode);
        appointmentModel.setIsDone(Boolean.TRUE);
        appointmentDb.save(appointmentModel);
        logger.info("Change appointment {} Status", appointmentModel.getKode());

        TagihanModel tagihanModel = new TagihanModel();
        tagihanModel.setAppointment(appointmentModel);
        tagihanModel.setJumlahTagihan(appointmentModel.getDokter().getTarif());
        tagihanModel.setTanggalTerbuat(LocalDateTime.now());
        tagihanModel.setIsPaid(false);
        tagihanDb.save(tagihanModel);
        logger.info("Created Tagihan {}", tagihanModel.getKode());

        return "redirect:/appointment/" + kode;
    }
}
