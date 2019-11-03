package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("my-users")
public class User {
  
  @JsonProperty("name")
  private Name name;

  @JsonProperty("login")
  private Login login;

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
