package apap.TA_C_SA_88.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class  UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "is_Sso", nullable = false)
    private Boolean isSso;

    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name="role", nullable = false)
    private String role;

}