package net.xaviersala.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Producte {
  
  @Id
  private String id;
  
  String nom;
  String descripcio;
  private String imatge;
  float preu;
  List<Categories> categories;
  /**
   * @return the id
   */
  public String getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
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
   * @return the descripcio
   */
  public String getDescripcio() {
    return descripcio;
  }
  /**
   * @param descripcio the descripcio to set
   */
  public void setDescripcio(String descripcio) {
    this.descripcio = descripcio;
  }
  /**
   * @return the imatge
   */
  public String getImatge() {
    return imatge;
  }

  /**
   * @param imatge the imatge to set
   */
  public void setImatge(String imatge) {
    this.imatge = imatge;
  }
  /**
   * @return the preu
   */
  public float getPreu() {
    return preu;
  }
  /**
   * @param preu the preu to set
   */
  public void setPreu(float preu) {
    this.preu = preu;
  }
  /**
   * @return the categories
   */
  public List<Categories> getCategories() {
    return categories;
  }
  /**
   * @param categories the categories to set
   */
  public void setCategories(List<Categories> categories) {
    this.categories = categories;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return  nom + " " + imatge + "(" + categories + ")";
  }
    
}
