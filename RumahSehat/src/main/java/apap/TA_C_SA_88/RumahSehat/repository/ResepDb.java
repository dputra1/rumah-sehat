package apap.TA_C_SA_88.RumahSehat.repository;

import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResepDb extends JpaRepository<ResepModel,Long> {
}
