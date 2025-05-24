package LoginUtenti.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import LoginUtenti.dto.JwtResponse;
import LoginUtenti.dto.LoginRequest;
import LoginUtenti.dto.RegisterRequest;
import LoginUtenti.model.Ruolo;
import LoginUtenti.model.Utente;
import LoginUtenti.repository.UtenteRepository;
import LoginUtenti.security.UtenteDetailsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UtenteRepository utenteRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UtenteDetailsService utenteDetailsService;
	
	public JwtResponse registrazione(RegisterRequest registerRequest) {
		  if (utenteRepository.existsByEmail(registerRequest.getEmail())) {
		        throw new RuntimeException("Email già in uso");
		    }
		    if (utenteRepository.existsByNickname(registerRequest.getNickname())) {
		        throw new RuntimeException("Nickname già in uso");
		    }

		    Utente nuovoUtente = Utente.builder()
		        .withNickname(registerRequest.getNickname())
		        .withEmail(registerRequest.getEmail())
		        .withPassword(passwordEncoder.encode(registerRequest.getPassword()))
		        .withRuolo(Ruolo.UTENTE)
		        .build();

		    utenteRepository.save(nuovoUtente);
		    
		    UserDetails userDetails = utenteDetailsService.loadUserByUsername(registerRequest.getEmail());
		    String token = jwtService.generaToken(userDetails);

		    return new JwtResponse(
		        token,
		        "Bearer",
		        nuovoUtente.getNickname(),
		        nuovoUtente.getEmail(),
		        nuovoUtente.getRuolo()
		    );
	}
	
	public JwtResponse login(LoginRequest loginRequest) {
		 try {
		        System.out.println("Login tentato con email: " + loginRequest.getEmail());
		        authenticationManager.authenticate(
		            new UsernamePasswordAuthenticationToken(
		                loginRequest.getEmail(),
		                loginRequest.getPassword()
		            )
		        );

		        Utente utente = utenteRepository.findByEmail(loginRequest.getEmail())
		            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

		        UserDetails userDetails = utenteDetailsService.loadUserByUsername(loginRequest.getEmail());
		        String token = jwtService.generaToken(userDetails);

		        return new JwtResponse(
		            token,
		            "Bearer",
		            utente.getNickname(),
		            utente.getEmail(),
		            utente.getRuolo()
		        );
		    } catch (Exception e) {
		        System.err.println("Errore in login: " + e.getMessage());
		        e.printStackTrace();
		        throw e; 
		    }
	}

}
