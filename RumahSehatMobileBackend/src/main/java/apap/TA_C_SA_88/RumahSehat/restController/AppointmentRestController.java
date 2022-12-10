package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentRestService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/appointment")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/list-appointment")
        private List<AppointmentModel> retrieveListAppointment(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        return appointmentRestService.retrievePasienListAppointment(username);
    }
}
