package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.payload.NewAppointmentRequest;
import apap.TA_C_SA_88.RumahSehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> viewAllAppointment() { return appointmentDb.findAll(); }

    @Override
    public boolean checkAvailability(NewAppointmentRequest newAppointment){
        List<AppointmentModel> listAppointment = appointmentDb.findAll();
        LocalDateTime waktuAwalCek = newAppointment.getWaktuAwal();
        LocalDateTime waktuAkhirCek = newAppointment.getWaktuAwal().plusHours(1);
        for(AppointmentModel appointment : listAppointment){
            LocalDateTime waktuAwal = appointment.getWaktuAwal();
            LocalDateTime waktuAkhir = appointment.getWaktuAwal().plusHours(1);
            if((waktuAwalCek.isAfter(waktuAwal) && waktuAwalCek.isBefore(waktuAkhir)) || (waktuAkhirCek.isAfter(waktuAwal) && waktuAkhirCek.isBefore(waktuAkhir)) || waktuAwalCek.isEqual(waktuAwal)) {
                return false;
            };
        }
        return true;
    }

    @Override
    public AppointmentModel addAppointment(AppointmentModel appointment){
        return appointmentDb.save(appointment);
    }

    @Override
    public AppointmentModel findAppointmentById(String kode) {
        return appointmentDb.findById(kode).get();
    }
}
