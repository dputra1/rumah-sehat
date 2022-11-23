package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;

import java.util.List;

public interface ApotekerService {
    List<ApotekerModel> viewAllApoteker();
    void addApoteker(ApotekerModel apoteker);
    String encrypt(String pasword);
    ApotekerModel findByUsername(String apoteker);
}
