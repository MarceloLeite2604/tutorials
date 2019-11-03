package com.github.marceloleite2604.tutorials.spring.reactor.user.service.dao.repository;

import com.github.marceloleite2604.tutorials.spring.reactor.user.service.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserReactiveRepository extends ReactiveCrudRepository<User, String> {
}
