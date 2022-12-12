package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> viewAllAppointment() { return appointmentDb.findAll(); }

    @Override
    public AppointmentModel getAppointmentByKode(String kode) {
        AppointmentModel appointmentModel = appointmentDb.findByKode(kode);
        return appointmentModel;
    }
}
