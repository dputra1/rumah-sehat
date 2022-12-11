package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;

import java.util.List;
public interface AppointmentService {
    List<AppointmentModel> viewAllAppointment();
    void saveApp(AppointmentModel appointment);
    AppointmentModel getAppointmentById(Long Id);
}
