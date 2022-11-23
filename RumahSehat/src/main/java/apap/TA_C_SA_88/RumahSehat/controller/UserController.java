package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
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
public class UserController {
    @Qualifier("dokterServiceImpl")

    @Autowired
    private DokterService dokterService;

    @Qualifier("apotekerServiceImpl")

    @Autowired
    private ApotekerService apotekerService;

    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;

    @Qualifier("adminServiceImpl")

    @Autowired
    private AdminService adminService;

    @GetMapping("/user")
    public String userManagement(Model model) {
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        model.addAttribute("user", userLoggedIn);
        return "user";
    }

    @GetMapping("/user/dokterApoteker")
    public String viewAllDokterApoteker(Model model) {
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        List<DokterModel> listDokter = dokterService.viewAllDokter();
        List<ApotekerModel> listApoteker = apotekerService.viewAllApoteker();

        model.addAttribute("listDokter", listDokter);
        model.addAttribute("listApoteker", listApoteker);
        model.addAttribute("user", userLoggedIn);
        return "dokter-apoteker-viewall";
    }
    @GetMapping("/user/pasien")
    public String viewAllPasien(Model model) {
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();
        List<PasienModel> listPasien = pasienService.viewAllPasien();

        model.addAttribute("listPasien", listPasien);
        model.addAttribute("user", userLoggedIn);
        return "pasien-viewall";
    }
}
