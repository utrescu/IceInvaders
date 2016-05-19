package net.xaviersala.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.xaviersala.model.Producte;

public interface ProducteRepository extends PagingAndSortingRepository<Producte, String> { 
  
  public Producte findByNom(String nom);
  public Page<Producte> findByNom(String nom, Pageable page);
  
}
