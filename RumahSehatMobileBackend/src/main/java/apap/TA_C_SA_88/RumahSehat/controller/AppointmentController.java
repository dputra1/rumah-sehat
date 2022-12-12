package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String viewAllDokterApoteker(Model model) {
        List<AppointmentModel> listAppointment = appointmentService.viewAllAppointment();

        model.addAttribute("listAppointment", listAppointment);
        return "appointment-viewall";
    }
}
