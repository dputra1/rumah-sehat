package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import java.util.List;

public interface AdminService {
    List<AdminModel> viewAllAdmin();
    void addAdmin(AdminModel admin);
    String encrypt(String pasword);

    AdminModel findByUsername(String admin);
}
