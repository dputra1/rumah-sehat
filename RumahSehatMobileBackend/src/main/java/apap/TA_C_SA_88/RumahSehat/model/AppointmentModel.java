package apap.TA_C_SA_88.RumahSehat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentModel implements Serializable {
    @Id
    @GenericGenerator(name = "sequence_apt_id", strategy = "apap.TA_C_SA_88.RumahSehat.generator.AppointmentIdGenerator")
    @GeneratedValue(generator = "sequence_apt_id")  
    @Column(name="kode")
    private String kode;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name="uuid_pasien", referencedColumnName="uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name="uuid_dokter", referencedColumnName="uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    @OneToOne(mappedBy = "appointment")
    private TagihanModel tagihan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_resep", referencedColumnName = "id")
    private ResepModel resep;
}