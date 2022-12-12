package apap.TA_C_SA_88.RumahSehat.repository;

import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String>{
}
