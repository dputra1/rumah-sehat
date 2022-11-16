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

    @EmbeddedId
    private JumlahId id;

    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name="id_obat", referencedColumnName="idObat")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("id_obat")
    private ObatModel obat;

    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name="id_resep", referencedColumnName="id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("id_resep")
    private ResepModel resep;

    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;
}
