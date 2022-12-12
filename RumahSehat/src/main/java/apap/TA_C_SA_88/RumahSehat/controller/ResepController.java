package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.service.*;
import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.repository.AdminDb;
import apap.TA_C_SA_88.RumahSehat.repository.ApotekerDb;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ResepController {
    

    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    PasienDb pasienDb;

    @Autowired
    AdminDb adminDb;

    @Qualifier("adminServiceImpl")

    @Autowired
    AdminService adminService;

    @Qualifier("dokterServiceImpl")

    @Autowired
    private DokterService dokterService;

    @Qualifier("apotekerServiceImpl")

    @Autowired
    private ApotekerService apotekerService;

    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Qualifier("obatServiceImpl")

    @Autowired
    private ObatService obatService;

    @Autowired
    private AppointmentService appointmentService;

    Logger logger = LoggerFactory.getLogger(ResepController.class);

    @GetMapping("/resep/add-resep/{IdApp}")
    public String addResepFormPage(@PathVariable String IdApp, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
        model.addAttribute("user", userLoggedIn);

        ResepModel resep = new ResepModel();

        List<ObatModel> listObat = obatService.getListObat();
        List<JumlahModel> listJumlahNew = new ArrayList<>();

        resep.setListJumlah(listJumlahNew);
        resep.getListJumlah().add(new JumlahModel());

        model.addAttribute("IdApp",IdApp);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);


        return "form-add-resep";
    }

    @PostMapping(value = "/resep/add-resep/{IdApp}", params = {"addRow"})
    private String addRowObatMultiple(
            @PathVariable String IdApp, @ModelAttribute ResepModel resep,
            Model model
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
        model.addAttribute("user", userLoggedIn);


        if (resep.getListJumlah() == null || resep.getListJumlah().size()==0){
            resep.setListJumlah(new ArrayList<>());
        }

        resep.getListJumlah().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("IdApp",IdApp);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";


    }
    @PostMapping(value = "/resep/add-resep/{IdApp}", params = {"deleteRow"})
    private String deleteRowObatMultiple(@PathVariable String IdApp, @ModelAttribute ResepModel resep, final HttpServletRequest req, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
        model.addAttribute("user", userLoggedIn);
        
        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        resep.getListJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("IdApp",IdApp);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }


    @PostMapping("/resep/add-resep/{IdApp}")
    public String addResepSubmitPage(@PathVariable String IdApp, @ModelAttribute ResepModel resep, @ModelAttribute JumlahModel jumlah, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
        model.addAttribute("user", userLoggedIn);
        
        if (resep.getListJumlah() == null) {
            resep.setListJumlah(new ArrayList<>());
        }

        ResepModel resepModel = new ResepModel();
        AppointmentModel appointment = appointmentService.getAppointmentByKode(IdApp);
        if(appointment == null){
            model.addAttribute("IdApp", IdApp);
            return "appointment-notfound";
        }

        resepModel.setCreatedAt(LocalDateTime.now());
        resepModel.setIsDone(false);
        resepModel.setApoteker(null);
        resepModel.setAppointment(appointment);
        resepModel = resepService.addResep(resepModel);

        logger.info("Created resep {}",resep.getId());

        appointment.setResep(resepModel);
        appointmentService.saveApp(appointment);

        List<JumlahModel> listJumlah = new ArrayList<>();

        for (int i = 0; i < resep.getListJumlah().size(); i++) {

            JumlahModel jumlahModel = new JumlahModel();
            jumlahModel.setResep(resepModel);
            jumlahModel.setObat(resep.getListJumlah().get(i).getObat());
            jumlahModel.setKuantitas(resep.getListJumlah().get(i).getKuantitas());
            jumlahModel = resepService.addJumlah(jumlahModel);
            listJumlah.add(jumlahModel);

            // //ngurangin jumlah obat
            // ObatModel obatModel = resep.getListJumlah().get(i).getObat();
            // obatModel.setStok(obatModel.getStok()-resep.getListJumlah().get(i).getKuantitas());
            // obatService.save(obatModel);
        }

        resepModel.setListJumlah(listJumlah);
        resepModel = resepService.addResep(resepModel);
        model.addAttribute("resep", resepModel);
        return "add-resep";
    }


    @GetMapping("/resep")
    public String viewAllResep(Model model) {
        List<ResepModel> listResep = resepService.viewAllResep();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        if(adminService.findByUsername(username)!=null) {
            AdminModel userLoggedIn = adminService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }
        else if(apotekerService.findByUsername(username)!=null) {
            ApotekerModel userLoggedIn = apotekerService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }

        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }

    @GetMapping("resep/detail-resep/{id}")
    public String detailResep(@PathVariable Long id, Model model){
        ResepModel resep = resepService.findResepById(id);
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
        else if(apotekerService.findByUsername(username)!=null) {
            ApotekerModel userLoggedIn = apotekerService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }
        boolean isApoteker = user.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("Apoteker"));

        model.addAttribute("resep", resep);
        model.addAttribute("isApoteker", isApoteker);
        return "resep-detail";
    }

    @PostMapping("resep/detail-resep/{id}")
    public String konfirmasiResep(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        
        ApotekerModel userLoggedIn = apotekerService.findByUsername(username);
        model.addAttribute("user", userLoggedIn);
        
        ResepModel resep = resepService.findResepById(id);
        List<JumlahModel> listJumlah = resep.getListJumlah();
        for(JumlahModel jumlah : listJumlah){
            ObatModel obat = jumlah.getObat();
            obat.setStok(obat.getStok()-jumlah.getKuantitas());
            if(obat.getStok()<0){
                model.addAttribute("resep", resep);
                return "fail-konfirmasi-resep";
            }
        }

        for(JumlahModel jumlah : listJumlah){
            ObatModel obatDibeli = jumlah.getObat();
            obatService.save(obatDibeli);
        }

        resep.setIsDone(true);
        resepService.addResep(resep);

        model.addAttribute("resep", resep);
        return "konfirmasi-resep";
    }
}
