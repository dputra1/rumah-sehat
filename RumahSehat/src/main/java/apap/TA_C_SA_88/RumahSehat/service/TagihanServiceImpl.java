package apap.TA_C_SA_88.RumahSehat.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService{
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public List<List<TagihanModel>> getTagihanAllRentangUmur(){
        List<TagihanModel> rentang1 = new ArrayList<>();
        List<TagihanModel> rentang2 = new ArrayList<>();
        List<TagihanModel> rentang3 = new ArrayList<>();
        List<TagihanModel> rentang4 = new ArrayList<>();
        List<TagihanModel> rentang5 = new ArrayList<>();

        List<TagihanModel> listTagihan = tagihanDb.findAll();

        for(TagihanModel tagihan : listTagihan){
            AppointmentModel appointment = tagihan.getAppointment();
            PasienModel pasien = appointment.getPasien();
            int umur = pasien.getUmur();
            if(umur >= 0 && umur <=15){
                rentang1.add(tagihan);
            }else if(umur >= 16 && umur <=30){
                rentang2.add(tagihan);
            }else if(umur >= 31 && umur <=45){
                rentang3.add(tagihan);
            }else if(umur >= 46 && umur <=60){
                rentang4.add(tagihan);
            }else{
                rentang5.add(tagihan);
            }
        }

        List<List<TagihanModel>> allRentang = new ArrayList<>();
        allRentang.add(rentang1);
        allRentang.add(rentang2);
        allRentang.add(rentang3);
        allRentang.add(rentang4);
        allRentang.add(rentang5);
        
        return allRentang;
    }
}
