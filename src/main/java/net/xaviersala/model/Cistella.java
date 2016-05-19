package net.xaviersala.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cistelles")
public class Cistella {
  
  Date dia;
  List<Venda> vendes;
  String username;
  float total;
  
  @Id
  private String id;
  
  /**
   * Crea una cistella pels usuaris no autenticats.
   */
  public Cistella() {
    dia = new Date();
    vendes = new ArrayList<>();
    username="anomymous";
    total = 0;
  }
  
  /**
   * Crea una cistella per l'usuari determinat.
   * 
   * @param usuari nom de l'usuari
   */
  public Cistella(String usuari) {
    this();
    username = usuari;
  }

    
  /**
   * Retorna l'ID de la cistella.
   * 
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Retorna l'usuari de la cistella.
   * 
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
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the dia
   */
  public Date getDia() {
    return dia;
  }

  /**
   * @param dia the dia to set
   */
  public void setDia(Date dia) {
    this.dia = dia;
  }

  /**
   * @return the vendes
   */
  public List<Venda> getVendes() {
    return vendes;
  }

  /**
   * @param vendes the vendes to set
   */
  public void setVendes(List<Venda> vendes) {
    this.vendes = vendes;
  }
  
  public float getTotal() {
    return total;
  }
  
  /**
   * @param total the total to set
   */
  public void setTotal(float total) {
    this.total = total;
  }

  /**
   * Afegeix una línia de venda a la cistella.
   * @param venda producte i quantitat
   */
  public void afegirProducte(Venda venda) {
    vendes.add(venda);    
    total = getTotalCistella();
  }
  
  public boolean teVendes() {
    return !vendes.isEmpty();
  }
  
  /**
   * Calcula el total de la venda. 
   * @return obtenir el preu total de la cistella
   */
  public float getTotalCistella() {
    float sumaVendes = 0;
    for(Venda venda: vendes) {
      sumaVendes = sumaVendes + (venda.getQuantitat() * venda.getProducte().getPreu());
    }
    return sumaVendes;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Cistella: " + dia + " " + vendes;
  }



}
