package LoginUtenti.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;


@Service
@RequiredArgsConstructor
public class JwtService {

	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String estraiEmailDaToken(String token) {
        return estraiClaim(token, Claims::getSubject);
    }

    public boolean tokenValido(String token, UserDetails userDetails) {
        final String email = estraiEmailDaToken(token);
        return email.equals(userDetails.getUsername()) && !tokenScaduto(token);
    }
    
    private boolean tokenScaduto(String token) {
        return estraiClaim(token, Claims::getExpiration).before(new Date());
    }

    public <T> T estraiClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = estraiTuttiIClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims estraiTuttiIClaims(String token) {
        return Jwts
            .parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String generaToken(UserDetails userDetails) {
        return generaToken(Map.of(), userDetails);
    }

    public String generaToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
            .builder()
            .subject(userDetails.getUsername())
            .claims(extraClaims)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expirationMillis))
            .signWith(key)
            .compact();
    }
}
