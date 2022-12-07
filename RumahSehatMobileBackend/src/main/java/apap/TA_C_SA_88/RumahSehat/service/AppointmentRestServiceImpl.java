package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.repository.AppointmentDb;
import apap.TA_C_SA_88.RumahSehat.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService{
    @Autowired
    private AppointmentDb appointmentDb;

    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;

    @Qualifier("pasienRestServiceImpl")

    @Autowired
    private PasienRestService pasienRestService;

    @Override
    public List<AppointmentModel> retrievePasienListAppointment() {
        Authentication auth = pasienRestService.getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String username = userDetails.getUsername();
        PasienModel pasien = pasienService.getPasienByUsername(username);
        System.out.println(pasienService.viewAllPasienAppointment(pasien));
        return pasienService.viewAllPasienAppointment(pasien);
    }
}
