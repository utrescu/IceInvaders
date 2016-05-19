package net.xaviersala.config;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.xaviersala.model.Usuari;
import net.xaviersala.repositories.UsuariService;

@Component
public class UserMongoAuthenticatorProvider implements AuthenticationProvider {
  
  private static final Log log = LogFactory.getLog(UserMongoAuthenticatorProvider.class);
  
  @Autowired
  UsuariService usuaris;
  
  public UserMongoAuthenticatorProvider() {
    super();
  }

  @Override
  public Authentication authenticate(final Authentication authentication) {
    String nomUsuari = authentication.getName();
    String textContrasenya = authentication.getCredentials().toString();
    
    // No ha posat la contrasenya
    if (!StringUtils.hasText(textContrasenya)) {
      log.warn("Username {}: no password provided" + nomUsuari);      
      return null;
    }
    // Localitza l'usuari
    Usuari usuariIdentificat = usuaris.identifica(nomUsuari, textContrasenya);
    if (usuariIdentificat == null) {
      log.warn("Usuari " + nomUsuari + " no trobat");
      // Potser hauria de fer una cosa com throw new BadCredentialsException("user or password incorrect");
      return null;
    }

    List<GrantedAuthority> grantedAuths = usuariIdentificat.getAuthorities();    
    
    return new UsernamePasswordAuthenticationToken(
        usuariIdentificat.getUsername(), 
        usuariIdentificat.getContrasenya(), 
        grantedAuths);    
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
