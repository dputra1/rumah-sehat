package apap.TA_C_SA_88.RumahSehat.repository;

import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumlahDb extends JpaRepository<JumlahModel, String> {


}
