package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
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
    public void addDokter(DokterModel dokter) {dokterDb.save(dokter);
    }

    @Override
    public DokterModel getDokterByUsername(String username) {
        return dokterDb.findByUsername(username);
    }

    @Override
    public List<AppointmentModel> viewAllDokterAppointment(DokterModel dokter) {
        return dokter.getListAppointment();
    }
}
