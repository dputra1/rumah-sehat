package apap.TA_C_SA_88.RumahSehat.service;

import java.util.List;

import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;

public interface ResepService {
    List<ResepModel> viewAllResep();
    void addResep (ResepModel resep);
    void addJumlah(JumlahModel jumlah);
}