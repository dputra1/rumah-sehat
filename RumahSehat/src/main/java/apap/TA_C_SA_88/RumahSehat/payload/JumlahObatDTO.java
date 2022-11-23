package apap.TA_C_SA_88.RumahSehat.payload;

import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import lombok.Data;

@Data
public class JumlahObatDTO {
    private String obat;
    private Integer kuantitas;
}
