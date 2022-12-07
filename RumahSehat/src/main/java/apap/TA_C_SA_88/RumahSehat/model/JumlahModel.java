package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.stream.events.StartElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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


    // Relation dengan ObatModel
    @ManyToOne
    @JoinColumn(name = "obat", referencedColumnName = "id_obat", nullable = false)
    private ObatModel obat;


    // Relation dengan ResepModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resep", referencedColumnName = "id", nullable = false)
    private ResepModel resep;


    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;
}
