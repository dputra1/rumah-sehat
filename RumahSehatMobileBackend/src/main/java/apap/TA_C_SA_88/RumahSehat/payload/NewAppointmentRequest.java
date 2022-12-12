package apap.TA_C_SA_88.RumahSehat.payload;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NewAppointmentRequest {
    private LocalDateTime waktuAwal;
    private String username;
}
