package LoginUtenti.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import LoginUtenti.model.Utente;

import java.util.*;


public class UtenteDetails implements UserDetails{
	
	private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    
    public UtenteDetails(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UtenteDetails build(Utente utente) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + utente.getRuolo().name());
        return new UtenteDetails(
            utente.getEmail(),
            utente.getPassword(),
            Collections.singletonList(authority)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Email come identificativo
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
