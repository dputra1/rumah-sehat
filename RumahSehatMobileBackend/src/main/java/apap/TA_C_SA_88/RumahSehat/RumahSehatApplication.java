package apap.TA_C_SA_88.RumahSehat;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;
import apap.TA_C_SA_88.RumahSehat.service.PasienService;

@SpringBootApplication
public class RumahSehatApplication {

	public static void main(String[] args) {
		SpringApplication.run(RumahSehatApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(PasienRestService pasienRestService) {
		return args -> {
			pasienRestService.addPasien(PasienModel.builder().email("fairuzsatriam@gmail.com")
			.isSso(false)
			.listAppointment(new ArrayList<>())
			.nama("fairuz")
			.password("fairuz")
			.role("Pasien")
			.saldo(0)
			.username("fairuz")
			.umur(20)
			.build());
		};
	}

}
