package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import java.util.List;

public interface AppointmentRestService {
    List<AppointmentModel> retrievePasienListAppointment(String username);
}
