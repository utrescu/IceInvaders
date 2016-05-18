package net.xaviersala.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.xaviersala.model.Cistella;

public interface CistellaRepository extends MongoRepository<Cistella, String> {

    List<Cistella> findByUsername(String username);
    public long countByUsername(String username);
    Cistella findByIdAndUsername(String id, String username);
    
}
