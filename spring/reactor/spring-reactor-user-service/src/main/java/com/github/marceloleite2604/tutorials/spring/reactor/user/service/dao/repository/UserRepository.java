package com.github.marceloleite2604.tutorials.spring.reactor.user.service.dao.repository;

import com.github.marceloleite2604.tutorials.spring.reactor.user.service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
