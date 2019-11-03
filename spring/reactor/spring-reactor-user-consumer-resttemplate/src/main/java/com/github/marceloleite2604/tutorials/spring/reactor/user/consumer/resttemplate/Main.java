package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate;

import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.exception.SpringReactorUserConsumerRuntimeException;
import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.model.User;
import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.properties.ProgramProperties;
import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.properties.UserServiceProperties;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.inject.Inject;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main implements CommandLineRunner {


  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  private static final String QUANTITY_PARAMETER_NAME = "quantity";

  private static final String LIST_USERS_SERVICE_PATH = "/api/list/users";

  @Inject
  private ProgramProperties programProperties;

  @Inject
  private UserServiceProperties userServiceProperties;

  @Inject
  private ConfigurableApplicationContext configurableApplicationContext;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Thread.sleep(5000);

    consumerRestTemplate();

    Thread.sleep(5000);

    exitProgram();
  }

  private void consumerRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    URI uri = createUserServiceUri(LIST_USERS_SERVICE_PATH);

    ParameterizedTypeReference<List<User>> userListparameterizedTypeReference =
        new ParameterizedTypeReference<List<User>>() {};

    ResponseEntity<List<User>> responseEntity =
        restTemplate.exchange(uri, HttpMethod.GET, null, userListparameterizedTypeReference);

    responseEntity.getBody().parallelStream().forEach(this::logUser);
  }

  private void exitProgram() {

    LOGGER.info("A total of {} users have been retrieved. Exiting.",
        programProperties.getUsersRetrieved());
    SpringApplication.exit(configurableApplicationContext, () -> 0);
  }

  public void logUser(User user) {
    LOGGER.info("User retrieved: {}", user);
  }

  private URI createUserServiceUri(String path) {
    try {
      String quantity = Integer.toString(programProperties.getUsersRetrieved());

      return new URIBuilder().setScheme(userServiceProperties.getScheme())
          .setPort(userServiceProperties.getPort()).setHost(userServiceProperties.getHost())
          .setPath(path).setParameter(QUANTITY_PARAMETER_NAME, quantity).build();
    } catch (URISyntaxException exception) {
      throw new SpringReactorUserConsumerRuntimeException(
          "Error while elaborating user service URI.", exception);
    }
  }
}
