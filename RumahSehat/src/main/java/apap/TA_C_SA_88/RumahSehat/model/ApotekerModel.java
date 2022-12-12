package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "apoteker")
public class ApotekerModel extends UserModel {
    
    @OneToMany(mappedBy = "apoteker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;

    // @ManyToOne(fetch = FetchType.EAGER, optional=false)
    // @JoinColumn(name="id_resep", referencedColumnName="id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @MapsId("id_resep")
    // private String idResep;
}
