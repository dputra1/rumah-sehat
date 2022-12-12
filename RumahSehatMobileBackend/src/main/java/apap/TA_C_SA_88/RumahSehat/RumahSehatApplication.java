package apap.TA_C_SA_88.RumahSehat;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import apap.TA_C_SA_88.RumahSehat.model.ApotekerModel;
import apap.TA_C_SA_88.RumahSehat.model.DokterModel;
import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.service.ApotekerService;
import apap.TA_C_SA_88.RumahSehat.service.DokterService;
import apap.TA_C_SA_88.RumahSehat.service.PasienRestService;

@SpringBootApplication
public class RumahSehatApplication {

	public static void main(String[] args) {
		SpringApplication.run(RumahSehatApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PasienRestService pasienRestService, DokterService dokterService, ApotekerService apotekerService) {
		return args -> {
			pasienRestService.addPasien(PasienModel.builder().email("testpasien@gmail.com")
			.isSso(false)
			.listAppointment(new ArrayList<>())
			.nama("testPasien")
			.password("pasientest")
			.role("Pasien")
			.saldo(0)
			.username("pasientest")
			.umur(20)
			.build());

			apotekerService.addApoteker(ApotekerModel.builder().email("testapoteker@gmail.com")
			.isSso(false)
			.nama("testApoteker")
			.password("apotekertest")
			.role("Apoteker")
			.username("apotekertest")
			.listResep(new ArrayList<>())
			.build());

			dokterService.addDokter(DokterModel.builder().email("testdokter@gmail.com")
			.isSso(false)
			.nama("testDokter")
			.password("doktertest")
			.role("Dokter")
			.username("doktertest")
			.tarif(200000)
			.listAppointment(new ArrayList<>())
			.build());
		};
	}

}
