package LoginUtenti.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import LoginUtenti.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final UtenteRepository utenteRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if (utenteRepository.count() == 0) { // solo se DB vuoto

			Utente admin = new Utente();
			admin.setEmail("admin@example.com");
			admin.setPassword(passwordEncoder.encode("adminpass"));
			admin.setRuolo(Ruolo.ADMIN);
			admin.setNickname("AdminUser");
			utenteRepository.save(admin);

			Utente moderatore = new Utente();
			moderatore.setEmail("moderatore@example.com");
			moderatore.setPassword(passwordEncoder.encode("modpass"));
			moderatore.setRuolo(Ruolo.MODERATORE);
			moderatore.setNickname("ModUser");
			utenteRepository.save(moderatore);

			Utente utente = new Utente();
			utente.setEmail("utente@example.com");
			utente.setPassword(passwordEncoder.encode("userpass"));
			utente.setRuolo(Ruolo.UTENTE);
			utente.setNickname("StandardUser");
			utenteRepository.save(utente);

			System.out.println("Utenti di default creati!");
		}
	}

}
