package net.xaviersala.model;

/**
 * @author xavier
 *
 */
public class Categories {
  
  String nom;

  public Categories() {
    nom = "general";
  }
  
  public Categories(String nom) {
    this.nom = nom;
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return nom;
  }
  
  
}
