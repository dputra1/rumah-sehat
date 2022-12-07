package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.ObatModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();

    ObatModel getObatByidObat(String idObat);

    ObatModel findObatById(String id);

    void save(ObatModel obatModel);

    ObatModel updateStok(ObatModel obat);
}
