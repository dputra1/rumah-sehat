package apap.TA_C_SA_88.RumahSehat.payload;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nama;
    private String email;
    private Integer umur;
    private String username;
    private String password;
}
