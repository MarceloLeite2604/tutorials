package org.marceloleite.tutorials.spring.mongo.embedded.dao.repository;

import java.util.UUID;

import org.marceloleite.tutorials.spring.mongo.embedded.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, UUID> {
}
