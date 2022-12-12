package apap.TA_C_SA_88.RumahSehat.repository;

import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, String>{
}
