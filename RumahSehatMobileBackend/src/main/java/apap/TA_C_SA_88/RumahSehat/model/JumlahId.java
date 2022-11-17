package apap.TA_C_SA_88.RumahSehat.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class JumlahId implements Serializable{
    
    @Range(max = 20)
    @Column(name = "id_obat")
    private String idObat;
 
    @Size(max = 10)
    @Column(name = "id_resep")
    private Long idResep;

}
