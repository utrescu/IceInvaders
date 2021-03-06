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
  public Usuari crearUsuari(UsuariDades dades) {
    
    Usuari nouUsuari = new Usuari();
    nouUsuari.setUsername(dades.getUsername());    
    nouUsuari.setContrasenya(passCode.encode(dades.getContrasenya() + nouUsuari.getSalt()));
    nouUsuari.setNom(dades.getNom());
    nouUsuari.setCognoms(dades.getCognoms());
    nouUsuari.setTelefon(dades.getTelefon());
    nouUsuari.setEmail(dades.getEmail());
    nouUsuari.setAdreca(dades.getAdreca());
    nouUsuari.setPoblacio(dades.getPoblacio());
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
   * Comprova si l'username està agafat o no.
   * @param nom username
   * @return true o false
   */
  public boolean usuariExisteix(String nom) {
    return usuaris.findByUsername(nom) != null;
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

  /**
   * Actualitza les dades de l'usuari especificat.
   * @param username usuari
   * @param dades dades de l'usuari
   */
  public void desaUsuari(String username, UsuariDades dades) {
     // Potser hauria de tornar un error si no el troba
     Usuari usuari = buscaUsuari(username);
     if (usuari != null) {
       usuari.setDades(dades);
       usuaris.save(usuari);
     } else {
       throw new RuntimeException();
     }
  }

  
}
