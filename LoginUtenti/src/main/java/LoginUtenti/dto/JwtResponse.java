package LoginUtenti.dto;


import LoginUtenti.model.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class JwtResponse {
	private String token;
    private String tokenType = "Bearer";
    private String nickname;
    private String email;
    private Ruolo ruolo;
}
