package net.xaviersala.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.xaviersala.model.Usuari;

public interface UsuariRepository extends MongoRepository<Usuari, String> {
  
  public Usuari findByUsername(String username);

}
