package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "obat")
public class ObatModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idObat;

    @NotNull
    @Column(name="nama_obat", nullable = false)
    private String namaObat;

    @NotNull
    @Column(name="stok", nullable = false)
    private Integer stok;

    @NotNull
    @Column(name="harga", nullable = false)
    private Integer harga;

    //relasi many to many ke resep
    @OneToMany(mappedBy = "obat", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JumlahModel> listJumlah;
}
