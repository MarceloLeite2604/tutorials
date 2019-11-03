package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.properties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.USER_SERVICE)
@Validated
public class UserServiceProperties {

  /**
   * Scheme to use for user API communication.
   */
  @NotBlank
  private String scheme;

  /**
   * User API host address.
   */
  @NotBlank
  private String host;

  /**
   * User API communication port.
   */
  @Min(1L)
  private int port;

  public String getScheme() {
    return scheme;
  }

  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
