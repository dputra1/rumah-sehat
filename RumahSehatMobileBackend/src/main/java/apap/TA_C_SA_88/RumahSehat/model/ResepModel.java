package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resep")
public class ResepModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_done")
    private Boolean isDone;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name="uuid_apoteker", referencedColumnName="uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apoteker;

    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<JumlahModel> listJumlah;

    @OneToOne(mappedBy = "resep")
    private AppointmentModel appointment;
    //relasi one-to-many ke apoteker
    // @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    // private List<ApotekerModel> id_apoteker;
}
