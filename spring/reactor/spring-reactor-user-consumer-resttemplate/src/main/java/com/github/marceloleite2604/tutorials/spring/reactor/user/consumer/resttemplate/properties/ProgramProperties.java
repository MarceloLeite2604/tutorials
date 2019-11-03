package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.properties;

import javax.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.PROGRAM)
@Validated
public class ProgramProperties {

  /**
   * Amount of users to retrieve from service.
   */
  @Min(1L)
  private int usersRetrieved;

  public int getUsersRetrieved() {
    return usersRetrieved;
  }

  public void setUsersRetrieved(int usersRetrieved) {
    this.usersRetrieved = usersRetrieved;
  }
}
