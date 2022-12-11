package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;


@RestController
@RequestMapping("/api/pasien/")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    @Autowired
    JwtUtils jwtUtils;

    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;

    Logger logger = LoggerFactory.getLogger(PasienRestController.class);
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
            return pasienRestService.addPasien(pasien);
        }
    }

    @GetMapping(value = "/getPasien")
    private PasienModel getPasien(@RequestHeader("Authorization") String token){
        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
        try {
            PasienModel pasien = pasienService.getPasienByUsername(username);
            logger.info("Order Success at API Profile - retrieveProfile");
            return pasien;
        }  catch (Exception e) {
            logger.error("Order Failed at API PROFILE - ", new Exception("retrieveProfile Exception"));
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "username " + username + " tidak ditemukan pada database"
            );
        }
    }

    @GetMapping(value = "/list-pasien")
    private List<PasienModel> retrieveListCourse(){
        return pasienRestService.retrieveListPasien();
    }

    @PutMapping("/updateSaldoPasien")
    private PasienModel updateSaldo(@RequestHeader("Authorization") String token, @RequestBody PasienModel pasien){
        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
        try {
            return pasienRestService.updateSaldoPasien(username, pasien);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien dengan username " + username + " tidak ditemukan");
        }
    }
}
