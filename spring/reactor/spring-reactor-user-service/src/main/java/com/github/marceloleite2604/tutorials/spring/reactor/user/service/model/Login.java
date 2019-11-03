package com.github.marceloleite2604.tutorials.spring.reactor.user.service.model;

public class Login {

  private String uuid;

  private String username;

  private String password;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Login [uuid=" + uuid + ", username=" + username + ", password=" + password + "]";
  }
}
