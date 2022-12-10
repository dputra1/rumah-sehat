package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import apap.TA_C_SA_88.RumahSehat.model.AppointmentModel;
import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.service.AppointmentRestService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
import apap.TA_C_SA_88.RumahSehat.service.TagihanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tagihan")
public class TagihanRestController {
    @Autowired
    @Qualifier("tagihanRestServiceImpl")
    private TagihanRestService tagihanRestService;

    @GetMapping(value = "/getall")
    public List<TagihanModel> getAllTagihan() {
        return tagihanRestService.getAllTagihan();
    }
}
