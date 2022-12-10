package apap.TA_C_SA_88.RumahSehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.stream.events.StartElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name="uuid_apoteker", referencedColumnName="uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apoteker;

    @OneToOne(mappedBy = "resep")
    @JsonIgnore
    private AppointmentModel appointment;

    @OneToMany(mappedBy = "idObat", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<ObatModel> listObatResep;

    //relasi one-to-many ke apoteker
    // @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    // private List<ApotekerModel> id_apoteker;
}
