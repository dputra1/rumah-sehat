package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.service.ResepService;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.service.ObatService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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


    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @GetMapping("/user/add-resep")
    public String addResepFormPage(Model model){
        ResepModel resep = new ResepModel();
        List<ObatModel> listObatModel = new ArrayList<>();

        List<ObatModel> listobat = obatService.getListObat();

        resep.setListObatResep(listObatModel);
        resep.getListObatResep().add(new ObatModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObat",listobat);

        return "form-add-resep";
    }

    @PostMapping("/user/add-resep")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, Model model){
        resepService.addResep(resep);
        ApotekerModel apoteker = new ApotekerModel();
        apoteker.setUuid("ff80808184948a330184948fba1b0000");
        apotekerService.addApoteker(apoteker);
        resep.setIsDone(false);
        resep.setCreatedAt(LocalDateTime.now());
        resep.setApoteker(apoteker);
        model.addAttribute("resep", resep);

        return "add-resep";
    }
}
