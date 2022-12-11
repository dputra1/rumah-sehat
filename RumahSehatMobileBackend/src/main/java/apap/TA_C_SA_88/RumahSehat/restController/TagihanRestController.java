package apap.TA_C_SA_88.RumahSehat.restController;

import java.util.List;
import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.service.TagihanRestService;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tagihan")
public class TagihanRestController {
    @Autowired
    private TagihanRestService tagihanRestService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/getAllTagihanUser")
    private List<TagihanModel> getAllTagihan(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        return tagihanRestService.getUserTagihan(username);
    }
}
