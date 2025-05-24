package LoginUtenti.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import LoginUtenti.dto.RuoloDTO;
import LoginUtenti.dto.UtenteDTO;
import LoginUtenti.model.Utente;
import LoginUtenti.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class UtenteController {
	

    private final UtenteRepository utenteRepository;
	
	@GetMapping("/profilo")
	public ResponseEntity<String> profilo() {
		return ResponseEntity.ok("Accesso utente autenticato (qualsiasi ruolo)");
	}

	@GetMapping("/admin")
	public ResponseEntity<String> adminAccess() {
		return ResponseEntity.ok("Accesso area ADMIN");
	}

	@GetMapping("/mod")
	public ResponseEntity<String> modAccess() {
		return ResponseEntity.ok("Accesso area MODERATORE");
	}
	

    @GetMapping("/lista")
    @PreAuthorize("hasAnyRole('MODERATORE', 'ADMIN')")
    public ResponseEntity<List<UtenteDTO>> listaUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        List<UtenteDTO> utentiDto = utenti.stream()
            .map(u -> new UtenteDTO(u.getId(), u.getNickname(), u.getEmail(), u.getRuolo()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(utentiDto);
    }
    
    @PutMapping("/{id}/ruolo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> cambiaRuoloUtente(@PathVariable int id, @RequestBody RuoloDTO ruolo) {
        Utente utente = utenteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        utente.setRuolo(ruolo.getRuolo());
        utenteRepository.save(utente);
        return ResponseEntity.ok("Ruolo aggiornato con successo");
    }
}
