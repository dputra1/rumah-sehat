package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;

@RestController
@RequestMapping("/api/dokter")
public class DokterRestController {
    @Autowired
    DokterService dokterService;

    Logger logger = LoggerFactory.getLogger(DokterRestController.class);

    @GetMapping(value = "/list-dokter")
    private List<DokterModel> retrieveListDokter(){
        logger.info("Fetch list dokter");
        return dokterService.viewAllDokter();
    }



}
