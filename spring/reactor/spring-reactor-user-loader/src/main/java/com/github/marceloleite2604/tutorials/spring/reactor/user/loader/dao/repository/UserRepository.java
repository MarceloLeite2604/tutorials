package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.dao.repository;

import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

}
