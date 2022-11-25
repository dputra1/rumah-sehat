package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;


@RestController
@RequestMapping("/pasien/")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    @PostMapping(value = "/register")
    private PasienModel createPengajar(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }else{
            pasien.setListAppointment(new ArrayList<>());
            pasien.setRole("Pasien");
            pasien.setSaldo(0);
            pasien.setIsSso(false);;
            pasien.setPassword(pasienRestService.encrypt(pasien.getPassword()));
            return pasienRestService.addPasien(pasien);
        }
    }

    @GetMapping(value = "/list-pasien")
    private List<PasienModel> retrieveListCourse(){
        return pasienRestService.retrieveListPasien();
    }


}
