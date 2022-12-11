package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import apap.TA_C_SA_88.RumahSehat.payload.MessageResponse;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;


@RestController
@CrossOrigin
@RequestMapping("/api/pasien/")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasienService pasienService;
    @Autowired
    private PasienDb pasienDb;

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

    @GetMapping(value = "/list-pasien")
    private List<PasienModel> retrieveListCourse(){
        return pasienRestService.retrieveListPasien();
    }

    @PostMapping(value = "/top-up")
    public ResponseEntity<?> topupPasien(@RequestHeader("Authorization") String token, @RequestBody Map<String, Integer> payload){
        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
        PasienModel pasienModel = pasienService.getPasienByUsername(username);

        int addValue = payload.get("add");
        pasienModel.setSaldo(pasienModel.getSaldo() + addValue);
        pasienDb.save(pasienModel);

        return ResponseEntity.ok(new MessageResponse("Patient TopUp Successfully"));
    }



}
