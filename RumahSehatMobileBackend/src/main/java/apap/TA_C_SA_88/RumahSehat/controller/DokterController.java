package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
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

import java.util.List;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")

    @Autowired
    private DokterService dokterService;

    @GetMapping("/user/add-dokter")
    public String addDokterFormPage(Model model){
        DokterModel dokter = new DokterModel();
        model.addAttribute("dokter", dokter);

        return "form-add-dokter";
    }

    @PostMapping("/user/add-dokter")
    public String addDokterSubmitPage(@ModelAttribute DokterModel dokter, Model model){
        dokter.setRole("Dokter");
        dokter.setIsSso(false);
        dokterService.addDokter(dokter);
        model.addAttribute("dokter", dokter);

        return "add-dokter";
    }

    @GetMapping("dokter/viewAllAppointment")
    public String viewAllAppointment(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel dokter = dokterService.getDokterByUsername(username);

        List<AppointmentModel> listAppointment = dokterService.viewAllDokterAppointment(dokter);
        model.addAttribute("listAppointment", listAppointment);
        model.addAttribute("dokter", dokter);

        return "dokter-appointment-viewall";
    }


}
