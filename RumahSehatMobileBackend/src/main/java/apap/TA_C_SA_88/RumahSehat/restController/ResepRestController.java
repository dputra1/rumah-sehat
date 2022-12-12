package apap.TA_C_SA_88.RumahSehat.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.service.ResepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/resep")
public class ResepRestController {
    @Autowired
    private ResepService resepService;

    @GetMapping(value="/detail-resep/{id}")
    public ResepModel getDetailResep(@PathVariable Long id) {
        ResepModel resep = resepService.findResepById(id);
        return resep;
    }
    
}
