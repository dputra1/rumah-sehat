package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;

import java.util.List;

public interface DokterService {
    List<DokterModel> viewAllDokter();
    void addDokter(DokterModel dokter);
    DokterModel getDokterByUsername(String username);
    List<AppointmentModel> viewAllDokterAppointment(DokterModel dokter);
    String encrypt(String pasword);
}
