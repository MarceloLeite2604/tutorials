package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.webclient.model;

public class User {
  
  private Name name;

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
