package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> viewAllPasien(){
        return pasienDb.findAll();
    }

    @Override
    public PasienModel getPasienByUsername(String username) {
        return pasienDb.findPasienByUsername(username);
    }

    @Override
    public List<AppointmentModel> viewAllPasienAppointment(PasienModel pasien) {
        return pasien.getListAppointment();
    }
}
