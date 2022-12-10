package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.*;
import apap.TA_C_SA_88.RumahSehat.payload.JumlahObatDTO;
import apap.TA_C_SA_88.RumahSehat.service.*;

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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
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

    @Qualifier("appointmentServiceImpl")

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/resep/add-resep/{IdApp}")
    public String addResepFormPage(@PathVariable Long IdApp, Model model){
        ResepModel resep = new ResepModel();

        List<ObatModel> listObat = obatService.getListObat();
        List<JumlahModel> listJumlahNew = new ArrayList<>();

        resep.setListJumlah(listJumlahNew);
        resep.getListJumlah().add(new JumlahModel());


        //auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedin = adminService.findByUsername(username);

        model.addAttribute("IdApp",IdApp);
        model.addAttribute("user",userLoggedin);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);


        return "form-add-resep";
    }

    @PostMapping(value = "/resep/add-resep/{IdApp}", params = {"addRow"})
    private String addRowObatMultiple(
            @PathVariable Long IdApp, @ModelAttribute ResepModel resep,
            Model model
    ){
        if (resep.getListJumlah() == null || resep.getListJumlah().size()==0){
            resep.setListJumlah(new ArrayList<>());
        }

        resep.getListJumlah().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getListObat();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedin = adminService.findByUsername(username);

        model.addAttribute("user",userLoggedin);
        model.addAttribute("IdApp",IdApp);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";


    }
    @PostMapping(value = "/resep/add-resep/{IdApp}", params = {"deleteRow"})
    private String deleteRowObatMultiple(@PathVariable Long IdApp, @ModelAttribute ResepModel resep, final HttpServletRequest req, Model model){
        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        resep.getListJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedin = adminService.findByUsername(username);

        model.addAttribute("user",userLoggedin);
        model.addAttribute("IdApp",IdApp);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }


    @PostMapping("/resep/add-resep/{IdApp}")
    public String addResepSubmitPage(@PathVariable Long IdApp, @ModelAttribute ResepModel resep, @ModelAttribute JumlahModel jumlah, Model model){
        if (resep.getListJumlah() == null) {
            resep.setListJumlah(new ArrayList<>());
        }

        List<ResepModel> listResepModel = new ArrayList<>();
        ResepModel resepModel = new ResepModel();
        resepModel.setCreatedAt(LocalDateTime.now());
        resepModel.setIsDone(false);
        resepModel.setApoteker(null);
        AppointmentModel appointment = appointmentService.getAppointmentById(IdApp);
        appointment.setResep(resepModel);
        appointmentService.saveApp(appointment);
        System.out.println(IdApp);
        resepService.addResep(resepModel);
        listResepModel.add(resepModel);

        for (int i = 0; i < resep.getListJumlah().size(); i++) {

            JumlahModel jumlahModel = new JumlahModel();
            jumlahModel.setResep(resepModel);
            jumlahModel.setObat(resep.getListJumlah().get(i).getObat());
            jumlahModel.setKuantitas(resep.getListJumlah().get(i).getKuantitas());
            resepService.addJumlah(jumlahModel);

            //ngurangin jumlah obat
            ObatModel obatModel = resep.getListJumlah().get(i).getObat();
            obatModel.setStok(obatModel.getStok()-resep.getListJumlah().get(i).getKuantitas());
            obatService.save(obatModel);
        }

        //auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedin = adminService.findByUsername(username);

        model.addAttribute("user",userLoggedin);
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
}
