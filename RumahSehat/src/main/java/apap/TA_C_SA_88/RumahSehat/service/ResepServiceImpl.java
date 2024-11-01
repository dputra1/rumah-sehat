package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
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
    public ResepModel addResep(ResepModel resep) {return resepDb.save(resep);
    }

    @Override
    public JumlahModel addJumlah(JumlahModel jumlah) {
        return jumlahDb.save(jumlah);
    }

    @Override
    public List<ResepModel> viewAllResep() {
        return resepDb.findAll();
    }

    @Override
    public ResepModel findResepById(Long id) {
        return resepDb.findById(id).get();
    }
}
