package apap.TA_C_SA_88.RumahSehat.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import apap.TA_C_SA_88.RumahSehat.model.ResepModel;
import apap.TA_C_SA_88.RumahSehat.service.ResepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/resep")
public class ResepRestController {
    @Autowired
    private ResepService resepService;

    Logger logger = LoggerFactory.getLogger(ResepRestController.class);

    @GetMapping(value="/detail-resep/{id}")
    public ResepModel getDetailResep(@PathVariable Long id) {
        logger.info("Searched {} in resep", id);
        ResepModel resep = resepService.findResepById(id);
        return resep;
    }
    
}
