package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")

    @Autowired
    private DokterService dokterService;

    @Qualifier("adminServiceImpl")

    @Autowired
    private AdminService adminService;

    Logger logger = LoggerFactory.getLogger(DokterController.class);

    @GetMapping("/user/add-dokter")
    public String addDokterFormPage(Model model){
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        DokterModel dokter = new DokterModel();
        model.addAttribute("dokter", dokter);
        model.addAttribute("user", userLoggedIn);
        return "form-add-dokter";
    }

    @PostMapping("/user/add-dokter")
    public String addDokterSubmitPage(@ModelAttribute DokterModel dokter, Model model){
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();
        dokter.setRole("Dokter");
        dokter.setIsSso(false);
        dokterService.addDokter(dokter);

        logger.info("Created Dokter {}", dokter.getNama());
        model.addAttribute("dokter", dokter);
        model.addAttribute("user", userLoggedIn);
        return "add-dokter";
    }

    @GetMapping("/appointment/dokter")
    public String viewAllAppointment(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel dokter = dokterService.getDokterByUsername(username);

        List<AppointmentModel> listAppointment = dokterService.viewAllDokterAppointment(dokter);

        model.addAttribute("listAppointment", listAppointment);
        model.addAttribute("dokter", dokter);
        model.addAttribute("user", dokter);
        return "dokter-appointment-viewall";
    }
}
