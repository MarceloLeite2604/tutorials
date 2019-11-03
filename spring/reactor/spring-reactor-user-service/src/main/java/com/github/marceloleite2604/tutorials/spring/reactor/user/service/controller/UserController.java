package com.github.marceloleite2604.tutorials.spring.reactor.user.service.controller;

import com.github.marceloleite2604.tutorials.spring.reactor.user.service.model.User;
import com.github.marceloleite2604.tutorials.spring.reactor.user.service.service.UserService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api")
public class UserController {

  @Inject
  private UserService userService;

  @GetMapping(path = "/flux/users")
  public Flux<User> getFluxUsers(@RequestParam(defaultValue = "1") int quantity) {
    return userService.getFluxUsers(quantity);
  }

  @GetMapping(path = "/list/users")
  public List<User> getListUsers(@RequestParam(defaultValue = "1") int quantity) {
    return userService.getListUsers(quantity);
  }
}
