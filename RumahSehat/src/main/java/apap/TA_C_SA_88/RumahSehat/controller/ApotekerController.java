package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Controller
public class ApotekerController {
    @Qualifier("apotekerServiceImpl")

    @Autowired
    private ApotekerService apotekerService;

    @Qualifier("adminServiceImpl")

    @Autowired
    private AdminService adminService;

    Logger logger = LoggerFactory.getLogger(ApotekerController.class);

    @GetMapping("/user/add-apoteker")
    public String addApotekerFormPage(Model model){
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        ApotekerModel apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);
        model.addAttribute("user", userLoggedIn);

        return "form-add-apoteker";
    }

    @PostMapping("/user/add-apoteker")
    public String addApotekerSubmitPage(@ModelAttribute ApotekerModel apoteker, Model model){
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        apoteker.setRole("Apoteker");
        apoteker.setIsSso(false);
        apotekerService.addApoteker(apoteker);

        logger.info("Created Apoteker {}", apoteker.getNama());
        
        model.addAttribute("apoteker", apoteker);
        model.addAttribute("user", userLoggedIn);
        return "add-apoteker";
    }
}
