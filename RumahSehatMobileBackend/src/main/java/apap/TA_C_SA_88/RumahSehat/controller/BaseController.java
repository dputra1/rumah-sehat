package apap.TA_C_SA_88.RumahSehat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String Home() {
        return "home";
    }
}
