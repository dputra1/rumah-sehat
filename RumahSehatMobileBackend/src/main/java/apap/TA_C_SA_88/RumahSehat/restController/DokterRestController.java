package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.payload.NewAppointmentRequest;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;

@RestController
@RequestMapping("/api/dokter")
public class DokterRestController {
    @Autowired
    DokterService dokterService;

    @GetMapping(value = "/list-dokter")
    private List<DokterModel> retrieveListDokter(){
        return dokterService.viewAllDokter();
    }



}
