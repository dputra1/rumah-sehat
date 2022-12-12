package apap.TA_C_SA_88.RumahSehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
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
        pasien.setPassword(encrypt(pasien.getPassword()));
        return pasienDb.save(pasien);
    }

    private Authentication userAuthentication;
    
    @Override
    public List<PasienModel> retrieveListPasien(){
        return pasienDb.findAll();
    }

    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;

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
    public Authentication getAuthentication(){
        return this.userAuthentication;
    }

    public void setAuthentication(Authentication newAuthentication){
        this.userAuthentication = newAuthentication;
    }

    @Override
    public PasienModel updateSaldoPasien(String username, Integer update) {
        PasienModel pasien = pasienService.getPasienByUsername(username);
        pasien.setSaldo(update);
        return pasienDb.save(pasien);
    }

    @Override
    public PasienModel getPasien(String username) {
        return pasienDb.findByUsername(username).get();
    }

}
