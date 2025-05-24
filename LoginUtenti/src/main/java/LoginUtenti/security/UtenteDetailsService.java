package LoginUtenti.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import LoginUtenti.model.Utente;
import LoginUtenti.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteDetailsService implements UserDetailsService{
	private final UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con email: " + email));
        System.out.println("Caricato utente con email: " + utente.getEmail());
        System.out.println("Password DB: " + utente.getPassword());

        return UtenteDetails.build(utente);
    }
}
