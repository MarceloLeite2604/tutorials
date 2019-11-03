package com.github.marceloleite2604.tutorials.spring.reactor.user.service.dao;

import com.github.marceloleite2604.tutorials.spring.reactor.user.service.dao.repository.UserReactiveRepository;
import com.github.marceloleite2604.tutorials.spring.reactor.user.service.dao.repository.UserRepository;
import com.github.marceloleite2604.tutorials.spring.reactor.user.service.model.User;
import java.util.List;
import javax.inject.Inject;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UserDao {

  @Inject
  private UserReactiveRepository userReactiveRepository;

  @Inject
  private UserRepository userRepository;

  public Flux<User> findAllReactive() {
    return userReactiveRepository.findAll();
  }

  public List<User> findAllList(int quantity) {
    PageRequest pageRequest = PageRequest.of(0, quantity);
    return userRepository.findAll(pageRequest).toList();
  }
}
