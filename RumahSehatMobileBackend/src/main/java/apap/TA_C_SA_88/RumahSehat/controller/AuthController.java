package apap.TA_C_SA_88.RumahSehat.controller;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.payload.JwtResponse;
import apap.TA_C_SA_88.RumahSehat.payload.LoginRequest;
import apap.TA_C_SA_88.RumahSehat.payload.MessageResponse;
import apap.TA_C_SA_88.RumahSehat.payload.RegisterRequest;
import apap.TA_C_SA_88.RumahSehat.security.jwt.JwtUtils;
import apap.TA_C_SA_88.RumahSehat.security.services.UserDetailsImpl;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasienRestService pasienRestService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("test1");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("test2");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("test3");
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("test4");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUuid(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (pasienRestService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username sudah digunakan!"));
        }
        if (pasienRestService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username sudah digunakan!"));
        }

        // Create new user's account
        PasienModel addPasien = new PasienModel();
        addPasien.setNama(registerRequest.getNama());
        addPasien.setUsername(registerRequest.getUsername());
        addPasien.setPassword(pasienRestService.encrypt(registerRequest.getPassword()));
        addPasien.setEmail(registerRequest.getEmail());
        addPasien.setUmur(registerRequest.getUmur());
        addPasien.setRole("Pasien");
        addPasien.setSaldo(0);
        addPasien.setIsSso(false);
        pasienRestService.addPasien(addPasien);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
