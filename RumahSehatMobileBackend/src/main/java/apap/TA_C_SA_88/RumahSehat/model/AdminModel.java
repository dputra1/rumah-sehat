package apap.TA_C_SA_88.RumahSehat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class AdminModel extends UserModel {
    
}