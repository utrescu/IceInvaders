package net.xaviersala.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Document(collection = "usuaris")
public class Usuari {

  @Id
  private String id;

  String username;
  String contrasenya;
  String salt;
  List<String> roles;

  public Usuari() {
    salt = UUID.randomUUID().toString();
    roles = Arrays.asList("ROLE_USER");
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the contrasenya
   */
  public String getContrasenya() {
    return contrasenya;
  }

  /**
   * @param contrasenya
   *          the contrasenya to set
   */
  public void setContrasenya(String contrasenya) {
    this.contrasenya = contrasenya;
  }

  /**
   * @return the salt
   */
  public String getSalt() {
    return salt;
  }

  /**
   * @param salt
   *          the salt to set
   */
  public void setSalt(String salt) {
    this.salt = salt;
  }

  /**
   * @return the roles
   */
  public List<String> getRoles() {
    return roles;
  }

  /**
   * @param roles
   *          the roles to set
   */
  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  /**
   * @return Retorna els ROLES de l'usuari
   */
  public List<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorityList = new ArrayList<>();
    for (String role : roles) {
      authorityList.add(new SimpleGrantedAuthority(role));
    }
    return authorityList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Usuari [" + id + username + "(" + roles + ") ]";
  }

}
