package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public List<ApotekerModel> viewAllApoteker(){
        return apotekerDb.findAll();
    }

    @Override
    public void addApoteker(ApotekerModel apoteker) {
        String pass = encrypt(apoteker.getPassword());
        apoteker.setPassword(pass);
        apotekerDb.save(apoteker);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public ApotekerModel findByUsername(String apoteker) {
        return apotekerDb.findByUsername(apoteker);
    }

    @Override
    public ApotekerModel getApotekerLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        ApotekerModel userLoggedIn = findByUsername(username);

        return userLoggedIn;
    }
}
