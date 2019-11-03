package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.webclient;

import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.webclient.exception.SpringReactorUserConsumerRuntimeException;
import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.webclient.model.User;
import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.webclient.properties.ProgramProperties;
import com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.webclient.properties.UserServiceProperties;
import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class Main implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  private static final String QUANTITY_PARAMETER_NAME = "quantity";

  private static final String FLUX_USERS_SERVICE_PATH = "/api/flux/users";

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

    consumerWebClient();

    Thread.sleep(5000);

    exitProgram();
  }

  private void consumerWebClient() {
    WebClient webClient = WebClient.create();

    URI uri = createUserServiceUri(FLUX_USERS_SERVICE_PATH);

    webClient.get().uri(uri).retrieve().bodyToFlux(User.class)
        .take(programProperties.getUsersRetrieved()).parallel().runOn(Schedulers.parallel())
        .doOnNext(this::logUser).sequential().blockLast();
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
