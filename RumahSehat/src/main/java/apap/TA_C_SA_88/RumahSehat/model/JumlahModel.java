package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JoinColumn(name="id_obat", referencedColumnName="idObat")
    private ObatModel obat;

    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name="id_resep", referencedColumnName="id")
    private ResepModel resep;

    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;
}
