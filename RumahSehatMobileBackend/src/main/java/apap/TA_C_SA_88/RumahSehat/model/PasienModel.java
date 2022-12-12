package apap.TA_C_SA_88.RumahSehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "pasien")
public class PasienModel extends UserModel {
    @NotNull
    @Column(name = "saldo", nullable = false)
    private Integer saldo;

    @NotNull
    @Column(name = "umur", nullable = false)
    private Integer umur;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AppointmentModel> listAppointment;
}