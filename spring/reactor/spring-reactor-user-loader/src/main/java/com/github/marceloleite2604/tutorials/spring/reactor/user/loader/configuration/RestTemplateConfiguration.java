package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.configuration;

import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.exception.SpringReactorUserLoaderRuntimeException;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.properties.RandomUserProperties;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

@Configuration
public class RestTemplateConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateConfiguration.class);

  @Bean
  public UriTemplateHandler createUriTemplateHandler(RandomUserProperties randomUserProperties) {

    URI randomUserUri = createRandomUserUri(randomUserProperties);

    return new DefaultUriBuilderFactory(randomUserUri.toString());
  }

  private URI createRandomUserUri(RandomUserProperties randomUserProperties) {
    try {
      return new URIBuilder().setScheme(randomUserProperties.getScheme())
          .setHost(randomUserProperties.getHost()).build();
    } catch (URISyntaxException exception) {
      LOGGER.error("Could not create Random User URI with properties {}", randomUserProperties);
      throw new SpringReactorUserLoaderRuntimeException("Could not create Random User URI.",
          exception);
    }
  }

  @Bean("restTemplate")
  public RestTemplate createRestTemplate(UriTemplateHandler uriTemplateHandler) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setUriTemplateHandler(uriTemplateHandler);
    return restTemplate;
  }
}
