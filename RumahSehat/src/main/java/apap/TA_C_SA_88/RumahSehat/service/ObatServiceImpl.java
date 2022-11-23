package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
import apap.TA_C_SA_88.RumahSehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    ObatDb obatdb;

    public List<ObatModel> getListObat(){
        return obatdb.findAll();
    }

    @Override
    public ObatModel findObatById(String id) {
        return obatdb.findById(id).get();
    }

    @Override
    public void save(ObatModel obatModel) {
        obatdb.save(obatModel);
    }
}
