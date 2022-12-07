package apap.TA_C_SA_88.RumahSehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{

    @Autowired
    private PasienDb pasienDb;

    @Override
    public PasienModel addPasien(PasienModel pasien){
        pasien.setPassword(pasien.getPassword());
        return pasienDb.save(pasien);
    }

    @Override
    public List<PasienModel> retrieveListPasien(){
        return pasienDb.findAll();
    }

    @Override
    public String encrypt(String password) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String hashedPassword = passwordEncoder.encode(password);
      return hashedPassword;
    }

    @Override
    public boolean existsByUsername(String username) {
        return pasienDb.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return pasienDb.existsByEmail(email);
    }

    @Override
    public PasienModel getPasien(String username) {
        return pasienDb.findByUsername(username).get();
    }



}
