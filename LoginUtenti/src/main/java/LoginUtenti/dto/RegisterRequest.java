package LoginUtenti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
	private String nickname;
	private String email;
	private String password;
}
