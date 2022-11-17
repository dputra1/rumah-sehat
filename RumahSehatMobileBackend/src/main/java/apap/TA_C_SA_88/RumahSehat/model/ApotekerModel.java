package apap.TA_C_SA_88.RumahSehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apoteker")
public class ApotekerModel extends UserModel implements Serializable {
    
    @OneToMany(mappedBy = "apoteker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;

    // @ManyToOne(fetch = FetchType.EAGER, optional=false)
    // @JoinColumn(name="id_resep", referencedColumnName="id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @MapsId("id_resep")
    // private String idResep;
}
