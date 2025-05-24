package LoginUtenti.dto;

import LoginUtenti.model.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtenteDTO {
	
	private int id;
	private String nickname;
	private String email;
	private Ruolo ruolo;
	
}
