package net.xaviersala.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xaviersala.model.Usuari;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UsuariService {
  
  @Autowired 
  UsuariRepository usuaris;
  
  /**
   * Es fa servir per codificar les contrasenyes
   */
  @Autowired
  PasswordEncoder passCode;
  
  /**
   * Creem un usuari nou.
   * @param username username de l'usuari a crear
   * @param password contrasenya de l'usuari a crear
   * @return Usuari creat en la base de dades
   */
  public Usuari crearUsuari(String username, String password) {
    Usuari nouUsuari = new Usuari();
    nouUsuari.setUsername(username);
    nouUsuari.setContrasenya(passCode.encode(password + nouUsuari.getSalt()));
    return usuaris.save(nouUsuari);    
  }
  
  /**
   * Retorna l'usuari a partir del nom d'usuari.
   * @param nom nom d'usuari
   * @return l'usuari o null
   */
  public Usuari buscaUsuari(String nom) {
    return usuaris.findByUsername(nom);
  }
  
  /**
   * Comprova si l'usuari i contrasenya demanats es corresponen
   * amb algun usuari correcte.
   * 
   * @param nom username
   * @param contrasenya password
   * @return L'objecte usuari o null si està malament
   */
  public Usuari identifica(String nom, String contrasenya) {
    
    Usuari usuariTrobat = usuaris.findByUsername(nom);
    if (usuariTrobat == null) {
      return null;
    }
    
    String codificaContrasenya = contrasenya + usuariTrobat.getSalt();
    
    if (!passCode.matches(codificaContrasenya, usuariTrobat.getContrasenya())) {
      return null;
    }
    
    return usuariTrobat;
  }

  
}