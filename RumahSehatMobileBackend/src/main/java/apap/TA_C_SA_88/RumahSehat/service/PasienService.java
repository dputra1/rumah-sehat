package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;

import java.util.List;
import java.util.Optional;

public interface PasienService {
    List<PasienModel> viewAllPasien();
    PasienModel getPasienByUsername(String username);
    List<AppointmentModel> viewAllPasienAppointment(PasienModel pasien);
}
