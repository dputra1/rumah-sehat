package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")

    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("adminServiceImpl")

    @Autowired
    private AdminService adminService;

    @GetMapping("/appointment")
    public String viewAllAppointment(Model model) {
        AdminModel userLoggedIn = adminService.getAdminLoggedIn();

        List<AppointmentModel> listAppointment = appointmentService.viewAllAppointment();

        model.addAttribute("listAppointment", listAppointment);
        model.addAttribute("user", userLoggedIn);
        return "appointment-viewall";
    }
}
