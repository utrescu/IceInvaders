package net.xaviersala.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.xaviersala.model.Cistella;

public interface CistellaRepository extends MongoRepository<Cistella, String> {

}
