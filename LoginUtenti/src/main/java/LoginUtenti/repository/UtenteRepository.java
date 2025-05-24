package LoginUtenti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LoginUtenti.model.Utente;


public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	Optional<Utente> findByEmail(String email);
	Optional<Utente> findByNickname(String nickname);
	boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
