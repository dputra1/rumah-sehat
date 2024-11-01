package apap.TA_C_SA_88.RumahSehat.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import apap.TA_C_SA_88.RumahSehat.model.AdminModel;
import apap.TA_C_SA_88.RumahSehat.model.UserModel;
import apap.TA_C_SA_88.RumahSehat.repository.AdminDb;
import apap.TA_C_SA_88.RumahSehat.repository.ApotekerDb;
import apap.TA_C_SA_88.RumahSehat.repository.DokterDb;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;
import apap.TA_C_SA_88.RumahSehat.security.xml.ServiceResponse;
import apap.TA_C_SA_88.RumahSehat.service.AdminService;
import apap.TA_C_SA_88.setting.Setting;
import apap.TA_C_SA_88.RumahSehat.security.xml.Attributes;

@Controller
public class BaseController {
    @Autowired
    ServerProperties serverProperties;

    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    PasienDb pasienDb;

    @Autowired
    AdminDb adminDb;

    @Qualifier("adminServiceImpl")

    @Autowired
    AdminService adminService;

    @Qualifier("dokterServiceImpl")

    @Autowired
    private DokterService dokterService;

    @Qualifier("apotekerServiceImpl")

    @Autowired
    private ApotekerService apotekerService;

    private WebClient webClient = WebClient.builder().build();

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/")
    private String Home(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        if(dokterService.getDokterByUsername(username)!=null) {
            DokterModel userLoggedIn = dokterService.getDokterByUsername(username);
            model.addAttribute("user", userLoggedIn);
            logger.info("Dokter {} logged in", userLoggedIn.getUsername());
        }
        else if(adminService.findByUsername(username)!=null) {
            AdminModel userLoggedIn = adminService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
            logger.info("Admin {} logged in", userLoggedIn.getUsername());
        }
        else if(apotekerService.findByUsername(username)!=null) {
            ApotekerModel userLoggedIn = apotekerService.findByUsername(username);
            model.addAttribute("user", userLoggedIn);
            logger.info("Apoteker {} logged in", userLoggedIn.getUsername());
        }
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    ){

        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        AdminModel user = adminDb.findByUsername(username);

        if(user == null){
            user = new AdminModel();
            user.setEmail(username + "@ui.ac.id");
            user.setNama(attributes.getNama());
            user.setPassword("belajarbelajar");
            user.setUsername(username);
            user.setIsSso(true);
            user.setRole("Admin");
            adminService.addAdmin(user);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "belajarbelajar");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO(){
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = apotekerDb.findByUsername(principal.getName());
        if(user == null) {
            user = dokterDb.findByUsername(principal.getName());
        }
        if(user == null) {
            user = pasienDb.findByUsername(principal.getName());
        }
        if(user == null) {
            user = adminDb.findByUsername(principal.getName());
        }
        if (!user.getIsSso()){
            logger.info("{} {} logged out", user.getRole(), user.getNama());
            return new ModelAndView("redirect:/logout");
        }
        logger.info("{} {} logged out", user.getRole(), user.getNama());
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}