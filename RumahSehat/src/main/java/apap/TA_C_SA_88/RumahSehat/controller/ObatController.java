package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import apap.TA_C_SA_88.RumahSehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")

    @Autowired
    private ObatService obatService;

    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/obat/viewAll")
    public String viewAllObat(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        if(adminService.findByUsername(username)=null) {
            ApotekerModel userLoggedIn = apotekerService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }else if(apotekerService.findByUsername(username)=null){
            AdminModel userLoggedIn = adminService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
        }

        List<ObatModel> listObat = obatService.viewAllObat();
        model.addAttribute("listObat", listObat);
        return "obat-viewall";
    }

    @GetMapping("/obat/update-stok/{idObat}")
    public String updateObatFormPage(@PathVariable String idObat, Model model) {
        ApotekerModel userLoggedIn = apotekerService.getApotekerLoggedIn();
        ObatModel obat = obatService.getObatByidObat(idObat);
        model.addAttribute("obat", obat);
        model.addAttribute("user", userLoggedIn);
        return "form-update-stokobat";
    }

    @PostMapping("/obat/update-stok")
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model) {
        ApotekerModel userLoggedIn = apotekerService.getApotekerLoggedIn();
        ObatModel updatedObat = obatService.updateStok(obat);
        model.addAttribute("idObat", updatedObat.getIdObat());
        model.addAttribute("user", userLoggedIn);
        return "redirect:/obat/viewAll";
    }
}
