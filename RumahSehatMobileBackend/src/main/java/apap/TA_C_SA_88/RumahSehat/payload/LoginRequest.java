package apap.TA_C_SA_88.RumahSehat.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
