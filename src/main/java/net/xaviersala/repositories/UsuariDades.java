package net.xaviersala.repositories;

public class UsuariDades {
  
  String nom;
  String cognoms;
  String email;
  String telefon;
  String adreca;
  String poblacio;
  
  public UsuariDades() {
    // Default constructor to fill default form.
  }

  public UsuariDades(String nom, String cognoms, String email, String poblacio) {
    this.nom = nom;
    this.cognoms = cognoms;
    this.email = email;
    this.poblacio = poblacio;
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
