package apap.TA_C_SA_88.RumahSehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.repository.ResepDb;

@Service
@Transactional
public class ResepServiceImpl implements ResepService{
    @Autowired
    ResepDb resepDb;

    @Override
    public void addResep(ResepModel resep) {resepDb.save(resep);
    }

    @Override
    public ResepModel findResepById(Long id) {
        return resepDb.findById(id).get();
    }
}
