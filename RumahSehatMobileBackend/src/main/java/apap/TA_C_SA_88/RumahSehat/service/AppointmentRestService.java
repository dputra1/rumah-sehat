package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;

import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

public interface AppointmentRestService {
    List<AppointmentModel> retrievePasienListAppointment(String username);
}
