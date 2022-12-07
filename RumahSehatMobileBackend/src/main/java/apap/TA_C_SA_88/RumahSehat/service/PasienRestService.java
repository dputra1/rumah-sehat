package apap.TA_C_SA_88.RumahSehat.service;

import java.util.List;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;

public interface PasienRestService {
    PasienModel addPasien(PasienModel pasien);

    List<PasienModel> retrieveListPasien();

    String encrypt(String password);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    PasienModel getPasien(String username);
}
