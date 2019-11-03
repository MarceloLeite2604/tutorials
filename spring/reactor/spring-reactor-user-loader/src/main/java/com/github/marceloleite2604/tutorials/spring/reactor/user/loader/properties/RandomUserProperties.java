package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.properties;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.RANDOM_USER)
@Validated
public class RandomUserProperties {

  /**
   * Scheme used on Random User API.
   */
  @NotNull
  private String scheme;

  /**
   * Random User host name.
   */
  @NotNull
  private String host;

  public String getScheme() {
    return scheme;
  }

  public void setScheme(String schema) {
    this.scheme = schema;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }
}
