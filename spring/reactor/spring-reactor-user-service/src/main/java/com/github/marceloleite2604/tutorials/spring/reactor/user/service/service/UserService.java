package com.github.marceloleite2604.tutorials.spring.reactor.user.service.service;

import com.github.marceloleite2604.tutorials.spring.reactor.user.service.dao.UserDao;
import com.github.marceloleite2604.tutorials.spring.reactor.user.service.model.User;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UserService {

  @Inject
  private UserDao userDao;

  public Flux<User> getFluxUsers(int quantity) {
    return userDao.findAllReactive().take(quantity);
  }

  public List<User> getListUsers(int quantity) {
    return userDao.findAllList(quantity);
  }
}
