package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.payload.NewAppointmentRequest;

import java.util.List;
public interface AppointmentService {
    List<AppointmentModel> viewAllAppointment();
    boolean checkAvailability(NewAppointmentRequest newAppointment);
    AppointmentModel addAppointment(AppointmentModel appointment);
}
