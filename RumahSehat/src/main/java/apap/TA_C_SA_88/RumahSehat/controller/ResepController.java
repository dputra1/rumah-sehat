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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @GetMapping("/resep/add-resep")
    public String addResepFormPage(Model model){
        ResepModel resep = new ResepModel();
        List<ObatModel> listObatModel = new ArrayList<>();

        List<ObatModel> listobat = obatService.getListObat();

        resep.setListObatResep(listObatModel);
        resep.getListObatResep().add(new ObatModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObat",listobat);

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

        return "form-add-resep";
    }

    @PostMapping("/resep/add-resep")
    public String addResepSubmitPage(@ModelAttribute ("jumlahObatDTO") JumlahObatDTO jumlahObatDTO,
                                     Model model, Authentication authentication){

        // bikin model resep
        ResepModel resepModel = new ResepModel();
        resepModel.setApoteker(apotekerService.findByUsername(authentication.getName())); //ubah konfirmasi data uuid apoteker jadi nullable
        resepModel.setCreatedAt(LocalDateTime.now());
        resepModel.setIsDone(false);
        resepService.addResep(resepModel);

        // bikin model jumlah
        JumlahModel addJumlah = new JumlahModel();
        addJumlah.setId(new JumlahId(jumlahObatDTO.getObat(),resepModel.getId()));
        addJumlah.setResep(resepModel);
        addJumlah.setObat(obatService.findObatById(jumlahObatDTO.getObat()));
        addJumlah.setKuantitas(jumlahObatDTO.getKuantitas());
        resepService.addJumlah(addJumlah);

        //update stok obat
        ObatModel obatModel = obatService.findObatById(jumlahObatDTO.getObat());
        obatModel.setStok(obatModel.getStok()-jumlahObatDTO.getKuantitas());
        obatService.save(obatModel);

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

        model.addAttribute("resep", resepModel);
        return "add-resep";
    }

    @GetMapping("/resep")
    public String viewAllResep(Model model) {
        List<ResepModel> listResep = resepService.viewAllResep();

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

        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }
}
