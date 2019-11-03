package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.PROGRAM)
@Validated
public class ProgramProperties {

  /**
   * Time to wait after request random users (in milliseconds)
   */
  @Min(1L)
  private long waitInterval;
  
  /**
   * Amount of users to be retrieved each request.
   */
  @Max(5000L)
  @Min(1L)
  private int usersRetrieved;

  public long getWaitInterval() {
    return waitInterval;
  }

  public void setWaitInterval(long waitInterval) {
    this.waitInterval = waitInterval;
  }

  public int getUsersRetrieved() {
    return usersRetrieved;
  }

  public void setUsersRetrieved(int usersRetrieved) {
    this.usersRetrieved = usersRetrieved;
  }
}
