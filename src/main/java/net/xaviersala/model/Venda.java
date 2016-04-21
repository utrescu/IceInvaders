package net.xaviersala.model;

public class Venda {

  Producte producte;
  int quantitat;
  
  public Venda() {
   
  }
  
  public Venda(Producte producte, int quantitat) {
    this.producte = producte;
    this.quantitat = quantitat;
  }

  /**
   * @return the producte
   */
  public Producte getProducte() {
    return producte;
  }

  /**
   * @param producte the producte to set
   */
  public void setProducte(Producte producte) {
    this.producte = producte;
  }

  /**
   * @return the quantitat
   */
  public int getQuantitat() {
    return quantitat;
  }

  /**
   * @param quantitat the quantitat to set
   */
  public void setQuantitat(int quantitat) {
    this.quantitat = quantitat;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return  producte.getNom() + ": " + quantitat;
  }
  
  
}
