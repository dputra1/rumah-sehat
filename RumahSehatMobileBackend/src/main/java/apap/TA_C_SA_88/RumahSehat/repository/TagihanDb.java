package apap.TA_C_SA_88.RumahSehat.repository;

import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


@Repository
public interface TagihanDb extends JpaRepository<TagihanModel,String> {

    @Query("SELECT a.tagihan FROM AppointmentModel a where a.pasien.uuid = :code")
    Collection<TagihanModel> findTagihanUser(@Param("code") String code);

}
