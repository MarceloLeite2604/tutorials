package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.dao;

import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.dao.repository.UserRepository;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model.User;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UserDao {

  @Inject
  private UserRepository userRepository;

  public Flux<User> findAll() {
    return userRepository.findAll();
  }

  public Flux<User> saveAll(Iterable<User> user) {
    return userRepository.saveAll(user);
  }
}
