package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.repository.AdminDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDb adminDb;

    @Override
    public List<AdminModel> viewAllAdmin() {
        return adminDb.findAll();
    }

    @Override
    public void addAdmin(AdminModel admin) {
        String pass = encrypt(admin.getPassword());
        admin.setPassword(pass);
        adminDb.save(admin);
    }
    
    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public AdminModel findByUsername(String admin) {
        return adminDb.findByUsername(admin);
    }

    @Override
    public AdminModel getAdminLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        AdminModel userLoggedIn = findByUsername(username);

        return userLoggedIn;
    }
}
