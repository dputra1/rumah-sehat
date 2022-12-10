package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.JumlahId;
import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.payload.JumlahObatDTO;
import apap.TA_C_SA_88.RumahSehat.service.*;
import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
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


    @GetMapping("/resep/add-resep")
    public String addResepFormPage(Model model){
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

        model.addAttribute("user",userLoggedin);
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);



        return "form-add-resep";
    }

    @PostMapping(value = "/resep/add-resep", params = {"addRow"})
    private String addRowObatMultiple(
            @ModelAttribute ResepModel resep,
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

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }

    @PostMapping(value = "/resep/add-resep", params = {"deleteRow"})
    private String deleteRowObatMultiple(@ModelAttribute ResepModel resep, final HttpServletRequest req, Model model){
        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        resep.getListJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedin = adminService.findByUsername(username);

        model.addAttribute("user",userLoggedin);

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }


    @PostMapping("/resep/add-resep")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, @ModelAttribute JumlahModel jumlah, Model model){
        if (resep.getListJumlah() == null) {
            resep.setListJumlah(new ArrayList<>());
        }

        List<ResepModel> listResepModel = new ArrayList<>();
        ResepModel resepModel = new ResepModel();
        resepModel.setCreatedAt(LocalDateTime.now());
        resepModel.setIsDone(false);
        resepModel.setApoteker(null);
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

    @GetMapping("resep/detail-resep/{id}")
    public String detailResep(@PathVariable Long id, Model model){
        ResepModel resep = resepService.findResepById(id);
        model.addAttribute("resep", resep);
        return "resep-detail";
    }

    @PostMapping("resep/detail-resep/{id}")
    public String konfirmasiResep(@ModelAttribute ResepModel resep, Model model) {
        List<JumlahModel> listJumlah = resep.getListJumlah();
        for(JumlahModel jumlah : listJumlah){
            ObatModel obat = jumlah.getObat();
            obat.setStok(obat.getStok()-jumlah.getKuantitas());
            if(obat.getStok()<0){
                return "fail-konfirmasi-resep";
            }
        }
        for(JumlahModel jumlah : listJumlah){
            ObatModel obatDibeli = jumlah.getObat();
            obatService.save(obatDibeli);
        }
        return "konfirmasi-resep";
    }
}
