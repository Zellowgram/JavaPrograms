package LoginUtenti.controller;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import LoginUtenti.dto.JwtResponse;
import LoginUtenti.dto.LoginRequest;
import LoginUtenti.dto.RegisterRequest;
import LoginUtenti.service.AuthService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
    public ResponseEntity<JwtResponse> registraUtente(@RequestBody RegisterRequest request) {
        JwtResponse jwtResponse = authService.registrazione(request);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUtente(@RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.login(request);
        return ResponseEntity.ok(jwtResponse);
    }
}
