package net.xaviersala.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.xaviersala.model.Producte;

/**
 * Servei de localització de Productes.
 * 
 * @author xavier
 *
 */
@Service
public class ProducteService {
  
  @Autowired
  ProducteRepository repositoriProductes;
  
  /**
   * Localitza un producte a partir del seu identificador.
   * @param id identificador del producte
   * @return El producte o null
   */
  public Producte buscaProducte(String id) {
    return repositoriProductes.findOne(id);
  }
  
  
  /**
   * Cerca un producte a partir del seu nom.
   * @param nom nom del producte
   * @return
   */
  public Producte buscaProductePerNom(String nom) {
    return repositoriProductes.findByNom(nom);
  }
  
  /**
   * Localitza tots els productes de forma paginada.
   * @param pagina el número de pàgina
   * @param articlesPerPagina el número d'articles per pàgina
   * @return
   */
  public List<Producte> buscaTotsProductesPerPagines(int pagina, int articlesPerPagina) {
    Page<Producte> paginaDeProductes; 
    
    paginaDeProductes = repositoriProductes.findAll(new PageRequest(pagina, articlesPerPagina));
    return paginaDeProductes.getContent();
  }
  
  /**
   * Compta els productes, si es passa null els compta tots.
   * @param nom nom a cercar
   * @return
   */
  public long comptaProductes(String nom) {
    if (nom == null) {
      return repositoriProductes.count();
    } else {
      return repositoriProductes.countByNomContainingIgnoreCase(nom);
    }
  }
  
  /**
   * Busca tots els articles que tenen una part del nom dins seu.
   * @param nom nom a cercar
   * @param pagina pàgina que es vol
   * @param articlesPerPagina articles de cada pàgina
   * @return
   */
  public List<Producte> buscaProductesPerNomPerPagines(String nom, int pagina, int articlesPerPagina) {
    Page<Producte> paginaDeProductes; 
    
    paginaDeProductes = repositoriProductes.findByNomContainingIgnoreCase(nom, new PageRequest(pagina, articlesPerPagina));
    return paginaDeProductes.getContent();
    
  }
  
  
}
