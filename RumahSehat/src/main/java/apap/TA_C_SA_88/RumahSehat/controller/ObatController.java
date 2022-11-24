package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.ObatModel;
import apap.TA_C_SA_88.RumahSehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")

    @Autowired
    private ObatService obatService;

    @GetMapping("/obat/viewAll")
    public String viewAllObat(Model model) {
        List<ObatModel> listObat = obatService.viewAllObat();

        model.addAttribute("listObat", listObat);
        return "obat-viewall";
    }

    @GetMapping("/obat/update-stok/{idObat}")
    public String updateCourseFormPage(@PathVariable String idObat, Model model) {
        ObatModel obat = obatService.getObatByidObat(idObat);
        model.addAttribute("obat", obat);
        return "form-update-stokobat";
    }

    @PostMapping("/obat/update-stok")
    public String updateCourseSubmitPage(@ModelAttribute ObatModel obat, Model model) {
        ObatModel updatedObat = obatService.updateStok(obat);
        model.addAttribute("idObat", updatedObat.getIdObat());
        return "redirect:/obat/viewAll";
    }
}
