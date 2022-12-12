package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat(){
        return obatDb.findAll();
    }

    @Override
    public ObatModel getObatByidObat(String idObat){
        ObatModel obatModel = obatDb.findByIdObat(idObat);
        return obatModel;
    }

    @Override
    public ObatModel findObatById(String id) {
        return obatDb.findById(id).get();
    }

    @Override
    public void save(ObatModel obatModel) {
        obatDb.save(obatModel);
    }

    @Override
    public ObatModel updateStok(ObatModel obat) {
        obatDb.save(obat);
        return obat;
    }
}
