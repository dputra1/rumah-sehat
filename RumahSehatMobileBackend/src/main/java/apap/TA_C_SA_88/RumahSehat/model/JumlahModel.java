package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jumlah")
public class JumlahModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "id_obat")
    private ObatModel obat;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "id_resep")
    private ResepModel resep;

    private Integer kuantitas;
}
