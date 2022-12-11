package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;
import java.util.NoSuchElementException;

import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.service.TagihanRestService;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tagihan")
public class TagihanRestController {
    @Autowired
    private TagihanRestService tagihanRestService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/getAllTagihanUser")
    private List<TagihanModel> getAllTagihan(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
        return tagihanRestService.getUserTagihan(username);
    }

    @PutMapping("/updateStatusTagihan")
    private TagihanModel updateStatusTagihan(@RequestBody TagihanModel tagihan){
        try {
            return tagihanRestService.updateStatusTagihan(tagihan);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tagihan dengan tagihan " + tagihan.getKode() + " tidak ditemukan");
        }
    }
}
