package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
import apap.TA_C_SA_88.RumahSehat.repository.JumlahDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.repository.ResepDb;

@Service
@Transactional
public class ResepServiceImpl implements ResepService{
    @Autowired
    ResepDb resepDb;

    @Autowired
    JumlahDb jumlahDb;


    
    @Override
    public void addResep(ResepModel resep) {resepDb.save(resep);
    }

    @Override
    public void addJumlah(JumlahModel jumlah) {
        jumlahDb.save(jumlah);
    }

    @Override
    public List<ResepModel> viewAllResep() {
        // TODO Auto-generated method stub
        return resepDb.findAll();
    }

    @Override
    public ResepModel findResepById(Long id) {
        return resepDb.findById(id).get();
    }
}
