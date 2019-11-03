package com.github.marceloleite2604.tutorials.spring.reactor.user.loader;

import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.dao.RandomUserDao;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.dao.UserDao;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model.User;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.properties.ProgramProperties;
import java.time.Duration;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Main implements CommandLineRunner {


  private static final int ERROR_EXIT_CODE = 1;

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


  @Inject
  private RandomUserDao randomUserDao;

  @Inject
  private UserDao userDao;

  @Inject
  private ProgramProperties programProperties;

  @Inject
  private ConfigurableApplicationContext configurableApplicationContext;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Flux.interval(Duration.ZERO, Duration.ofMillis(programProperties.getWaitInterval()))
        .map(this::retrieveUsers).retryBackoff(10, Duration.ofSeconds(20), Duration.ofMinutes(2))
        .doOnError(this::logErrorAndExit).flatMap(userDao::saveAll).subscribe();
  }

  private List<User> retrieveUsers(long index) {
    List<User> randomUsers = randomUserDao.retrieveUsers(programProperties.getUsersRetrieved());
    logRandomUsers(randomUsers);
    return randomUsers;
  }

  public void logRandomUsers(List<User> users) {
    LOGGER.info("Retrieved {} users.", users.size());
  }

  public void logErrorAndExit(Throwable throwable) {
    LOGGER.error("Random users loader will be terminated due to an error.", throwable);
    SpringApplication.exit(configurableApplicationContext, () -> ERROR_EXIT_CODE);
  }
}
