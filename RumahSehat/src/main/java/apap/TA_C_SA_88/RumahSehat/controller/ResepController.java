package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.JumlahId;
import apap.TA_C_SA_88.RumahSehat.model.JumlahModel;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.payload.JumlahObatDTO;
import apap.TA_C_SA_88.RumahSehat.service.*;
import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Controller
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private AdminService adminService;


    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @GetMapping("/add-resep")
    public String addResepFormPage(Model model){
        ResepModel resep = new ResepModel();

        List<ObatModel> listObat = obatService.getListObat();
        List<ObatModel> listObatNew = new ArrayList<ObatModel>();

        System.out.println(listObat);

        listObatNew.add(new ObatModel());
        resep.setListObatResep(listObatNew);

        model.addAttribute("resep", resep);
        model.addAttribute("listObat",listObat);

        return "form-add-resep";
    }
    @RequestMapping(value = "/add-resep", method = RequestMethod.POST, params = {"addRow"})
    public String addRowResep(@ModelAttribute ResepModel resepModel, BindingResult bindingResult, Model model){
        if(resepModel.getListObatResep() == null){
            resepModel.setListObatResep(new ArrayList<ObatModel>());
        }
        resepModel.getListObatResep().add(new ObatModel());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep",resepModel);
        model.addAttribute("listObat", listObat);
        return "form-add-resep";
    }

    @PostMapping("/add-resep")
    public String addResepSubmitPage(@ModelAttribute ResepModel resepModel,
                                     @ModelAttribute ("kuantitas") List<String> kuantitas, Model model){
        if (resepModel.getListObatResep() != null){
            for (ObatModel obatModel : resepModel.getListObatResep()){
                System.out.println(obatModel.getIdObat());
                System.out.println(kuantitas.get(1));
            }
        }
        return null;
    }

//    @PostMapping("/add-resep")
//    public String addResepSubmitPage(@ModelAttribute ("jumlahObatDTO") JumlahObatDTO jumlahObatDTO,
//                                     Model model, Authentication authentication){
//
//        // bikin model resep
//        ResepModel resepModel = new ResepModel();
////        resepModel.setApoteker(apotekerService.findByUsername(authentication.getName())); //ubah konfirmasi data uuid apoteker jadi nullable
//        resepModel.setCreatedAt(LocalDateTime.now());
//        resepModel.setIsDone(false);
//        resepService.addResep(resepModel);
//
//        // bikin model jumlah
//        JumlahModel addJumlah = new JumlahModel();
//        addJumlah.setId(new JumlahId(jumlahObatDTO.getObat(),resepModel.getId()));
//        addJumlah.setResep(resepModel);
//        addJumlah.setObat(obatService.findObatById(jumlahObatDTO.getObat()));
//        addJumlah.setKuantitas(jumlahObatDTO.getKuantitas());
//        resepService.addJumlah(addJumlah);
//
//        //update stok obat
//        ObatModel obatModel = obatService.findObatById(jumlahObatDTO.getObat());
//        obatModel.setStok(obatModel.getStok()-jumlahObatDTO.getKuantitas());
//        obatService.save(obatModel);
//
//        model.addAttribute("resep", resepModel);
//        return "add-resep";
//    }


    @PostMapping(value = "/add-resep", params = {"deleteRow"})
    private String deleteRowPenyelenggaraMultiple(
            @ModelAttribute ResepModel resep,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        resep.getListObatResep().remove(rowId.intValue());
        List<ObatModel> listObat= obatService.getListObat();
        model.addAttribute("resep",resep);
        model.addAttribute("listObat", listObat);
        return "form-add-resep";
    }
}
