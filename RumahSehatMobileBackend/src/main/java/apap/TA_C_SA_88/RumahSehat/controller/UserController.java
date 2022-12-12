package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/user")
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

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/dokterApoteker")
    public String viewAllDokterApoteker(Model model) {
        List<DokterModel> listDokter = dokterService.viewAllDokter();
        List<ApotekerModel> listApoteker = apotekerService.viewAllApoteker();

        model.addAttribute("listDokter", listDokter);
        model.addAttribute("listApoteker", listApoteker);
        return "dokter-apoteker-viewall";
    }
    @GetMapping("/pasien")
    public String viewAllPasien(Model model) {
        List<PasienModel> listPasien = pasienService.viewAllPasien();

        model.addAttribute("listPasien", listPasien);
        return "pasien-viewall";
    }
}
