package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.repository.ObatDb;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> viewAllObat(){
        return obatDb.findAll();
    }

    @Override
    public ObatModel getObatByidObat(String idObat){
        ObatModel obatModel = obatDb.findByIdObat(idObat);
        return obatModel;
    }

    @Override
    public ObatModel updateStok(ObatModel obat) {
        obatDb.save(obat);
        return obat;
    }
}
