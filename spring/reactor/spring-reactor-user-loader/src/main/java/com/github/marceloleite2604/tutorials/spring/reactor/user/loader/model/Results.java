package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Results {

  @JsonProperty("results")
  private List<User> users;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Results [users=" + users + "]";
  }

}
