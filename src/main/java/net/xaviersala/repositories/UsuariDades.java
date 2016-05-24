package net.xaviersala.repositories;

import javax.validation.constraints.Size;

public class UsuariDades {
  
  @Size(min=3, max=30, message="Ha de tenir 3 caràcters com a mínim")
  String username;
  
  @Size(min=6, message="Ha de tenir 6 caràcters com a mínim")
  String contrasenya;
  @Size(min=6)
  String repeatContrasenya;
  
  String nom;
  String cognoms;
  String email;
  String telefon;
  String adreca;
  String poblacio;
  
  public UsuariDades() {
    // Default constructor to fill default form.    
  }
    
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getContrasenya() {
    return contrasenya;
  }

  /**
   * @param password the password to set
   */
  public void setContrasenya(String contrasenya) {
    this.contrasenya = contrasenya;
  }

  /**
   * @return the repeatPassword
   */
  public String getRepeatContrasenya() {
    return repeatContrasenya;
  }

  /**
   * @param repeatPassword the repeatPassword to set
   */
  public void setRepeatContrasenya(String repeatContrasenya) {
    this.repeatContrasenya = repeatContrasenya;
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
    if (nom == null) {
      this.nom = "";
    } else {
      this.nom = nom;
    }
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
    if (cognoms == null) {
      this.cognoms = "";
    } else {
      this.cognoms = cognoms;
    }
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
    this.email = (email == null)? "" : email;
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
    this.telefon = (telefon==null) ? "" : telefon;
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
    this.adreca = (adreca == null)? "" : adreca;
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
    this.poblacio = (poblacio == null) ? "" : poblacio;
  }
    
  
}
