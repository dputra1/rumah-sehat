package apap.TA_C_SA_88.RumahSehat.service;

import java.util.List;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import org.springframework.security.core.Authentication;

public interface PasienRestService {
    PasienModel addPasien(PasienModel pasien);

    List<PasienModel> retrieveListPasien();

    String encrypt(String password);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Authentication getAuthentication();

    void setAuthentication(Authentication newAuthentication);

    PasienModel updateSaldoPasien(String username, PasienModel newPasien);
}
