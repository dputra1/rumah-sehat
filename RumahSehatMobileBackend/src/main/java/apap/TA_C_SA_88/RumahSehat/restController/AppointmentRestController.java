package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;

import javax.validation.Valid;

import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.payload.NewAppointmentRequest;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import apap.TA_C_SA_88.RumahSehat.security.services.UserDetailsImpl;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentRestService;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin
public class AppointmentRestController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DokterService dokterService;

    @Autowired
    PasienService pasienService;

    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    JwtUtils jwtUtils;

    Logger logger = LoggerFactory.getLogger(AppointmentRestController.class);

    @PostMapping(value = "/add-appointment")
    private AppointmentModel addAppointment(@Valid @RequestBody NewAppointmentRequest appointmentRequest){
        if(!appointmentService.checkAvailability(appointmentRequest)){
            logger.info("Selected Appointment Schedule not Available");
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Jadwal yang dipilih tidak tersedia."
            );
        };
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        String username = user.getUsername();
        PasienModel pasien = pasienService.getPasienByUsername(username);

        List<AppointmentModel> listAppointment = appointmentService.viewAllAppointment();

        AppointmentModel appointment = new AppointmentModel();
        DokterModel dokter = dokterService.getDokterByUsername(appointmentRequest.getUsername());
        
        appointment.setDokter(dokter);
        appointment.setIsDone(false);
        appointment.setKode("APT-"+listAppointment.size());
        appointment.setPasien(pasien);
        appointment.setResep(null);
        appointment.setTagihan(null);
        appointment.setWaktuAwal(appointmentRequest.getWaktuAwal());
        logger.info("Created Appointment {}", appointment.getKode());
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping(value = "/list-appointment")
    private List<AppointmentModel> retrieveListAppointment(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
        logger.info("Searched Appointment for {}", username);
        return appointmentRestService.retrievePasienListAppointment(username);
    }

    @GetMapping(value="/detail-appointment/{kode}")
    public AppointmentModel getDetailAppointment(@PathVariable String kode) {
        AppointmentModel appointmentModel = appointmentService.findAppointmentById(kode);
        return appointmentModel;
    }
}
