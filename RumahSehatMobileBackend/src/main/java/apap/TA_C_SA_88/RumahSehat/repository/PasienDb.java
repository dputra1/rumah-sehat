package apap.TA_C_SA_88.RumahSehat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {
  Optional<PasienModel> findByUsername(String username);
  List<PasienModel> findAll();

  PasienModel findPasienByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
