package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.*;
import apap.TA_C_SA_88.RumahSehat.payload.JumlahObatDTO;
import apap.TA_C_SA_88.RumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Controller
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private AdminService adminService;


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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedIn = adminService.findByUsername(username);

        model.addAttribute("user", userLoggedIn);
        model.addAttribute("resep", resep);
        model.addAttribute("listObat",listobat);

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
        AdminModel userLoggedIn = adminService.findByUsername(username);

        model.addAttribute("user", userLoggedIn);

        model.addAttribute("resep", resepModel);
        return "add-resep";
    }

    @GetMapping("/resep")
    public String viewAllResep(Model model) {
        List<ResepModel> listResep = resepService.viewAllResep();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedIn = adminService.findByUsername(username);

        model.addAttribute("user", userLoggedIn);

        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }
}
