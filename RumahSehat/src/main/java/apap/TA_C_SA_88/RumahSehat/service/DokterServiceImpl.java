package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DokterServiceImpl implements DokterService{
    @Autowired
    DokterDb dokterDb;
    @Override
    public List<DokterModel> viewAllDokter(){
        return dokterDb.findAll();
    }

    @Override
    public void addDokter(DokterModel dokter) {
        String pass = encrypt(dokter.getPassword());
        dokter.setPassword(pass);
        dokterDb.save(dokter);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public DokterModel getDokterByUsername(String username) {
        return dokterDb.findByUsername(username);
    }

    @Override
    public List<AppointmentModel> viewAllDokterAppointment(DokterModel dokter) {
        return dokter.getListAppointment();
    }

    @Override
    public DokterModel getDokterLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel userLoggedIn = getDokterByUsername(username);

        return userLoggedIn;
    }
}
