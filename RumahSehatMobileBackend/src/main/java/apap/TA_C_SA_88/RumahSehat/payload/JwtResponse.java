package apap.TA_C_SA_88.RumahSehat.payload;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String uuid;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String uuid, String username, String email, List<String> roles) {
        this.token = token;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
