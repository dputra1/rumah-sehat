package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApotekerController {
    @Qualifier("apotekerServiceImpl")

    @Autowired
    private ApotekerService apotekerService;

    @GetMapping("/user/add-apoteker")
    public String addApotekerFormPage(Model model){
        ApotekerModel apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);

        return "form-add-apoteker";
    }

    @PostMapping("/user/add-apoteker")
    public String addApotekerSubmitPage(@ModelAttribute ApotekerModel apoteker, Model model){
        apoteker.setRole("Apoteker");
        apoteker.setIsSso(false);
        apotekerService.addApoteker(apoteker);
        model.addAttribute("apoteker", apoteker);

        return "add-apoteker";
    }
}
