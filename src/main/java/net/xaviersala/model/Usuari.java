package net.xaviersala.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import net.xaviersala.repositories.UsuariDades;

@Document(collection = "usuaris")
public class Usuari {

  @Id
  private String id;

  String username;
  String contrasenya;
  String nom;
  String cognoms;
  String email;
  String telefon;
  String adreca;
  String poblacio;
  
  Date dataCreacio;
  
  String salt;
  List<String> roles;

  public Usuari() {
    
    dataCreacio = new Date();    
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
   * @return the nom
   */
  public String getNom() {
    return nom;
  }

  /**
   * @param nom the nom to set
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * @return the cognoms
   */
  public String getCognoms() {
    return cognoms;
  }

  /**
   * @param cognoms the cognoms to set
   */
  public void setCognoms(String cognoms) {
    this.cognoms = cognoms;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the telefon
   */
  public String getTelefon() {
    return telefon;
  }

  /**
   * @param telefon the telefon to set
   */
  public void setTelefon(String telefon) {
    this.telefon = telefon;
  }

  /**
   * @return the adreca
   */
  public String getAdreca() {
    return adreca;
  }

  /**
   * @param adreca the adreca to set
   */
  public void setAdreca(String adreca) {
    this.adreca = adreca;
  }

  /**
   * @return the poblacio
   */
  public String getPoblacio() {
    return poblacio;
  }

  /**
   * @param poblacio the poblacio to set
   */
  public void setPoblacio(String poblacio) {
    this.poblacio = poblacio;
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

  
  /**
   * @return the dataCreacio
   */
  public Date getDataCreacio() {
    return dataCreacio;
  }

  /**
   * @param dataCreacio the dataCreacio to set
   */
  public void setDataCreacio(Date dataCreacio) {
    this.dataCreacio = dataCreacio;
  }

  public void setDades(UsuariDades dades) {
          
     nom = dades.getNom();
     cognoms = dades.getCognoms();
     telefon = dades.getTelefon();
     email = dades.getEmail();
     adreca = dades.getAdreca();
     poblacio = dades.getPoblacio();
  }
  
  public UsuariDades getDades() {
    UsuariDades dades = new UsuariDades();
    
    dades.setUsername(username);    
    dades.setNom(nom);
    dades.setCognoms(cognoms);
    dades.setAdreca(adreca);
    dades.setPoblacio(poblacio);
    dades.setEmail(email);
    dades.setTelefon(telefon);
    
    return dades;
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
