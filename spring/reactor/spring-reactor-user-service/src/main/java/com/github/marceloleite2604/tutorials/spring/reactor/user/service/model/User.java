package com.github.marceloleite2604.tutorials.spring.reactor.user.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("my-users")
public class User {
  
  @Id
  private String id;
  
  private Name name;

  private Login login;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Login getLogin() {
    return login;
  }

  public void setLogin(Login login) {
    this.login = login;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", login=" + login + "]";
  }
}
