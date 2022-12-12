package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService{

    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;

    @Qualifier("pasienRestServiceImpl")

    @Autowired
    private PasienRestService pasienRestService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public List<AppointmentModel> retrievePasienListAppointment(String username) {
        PasienModel pasien = pasienService.getPasienByUsername(username);
        return pasienService.viewAllPasienAppointment(pasien);
    }
}
