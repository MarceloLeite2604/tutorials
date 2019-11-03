package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.dao;

import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.exception.SpringReactorUserLoaderRuntimeException;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.exception.TooManyRequestsRuntimeException;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model.Results;
import com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model.User;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class RandomUserDao {


  private static final Logger LOGGER = LoggerFactory.getLogger(RandomUserDao.class);


  private static final String RESULTS_PROPERTY_NAME = "results";
  private static final String RANDOM_USER_PATH_AND_QUERY_TEMPLATE =
      "/api/?results=${" + RESULTS_PROPERTY_NAME + "}";

  @Inject
  private RestTemplate restTemplate;

  public List<User> retrieveUsers(int quantity) {

    String pathAndQuery = elaboratePathAndQuery(quantity);
    try {
      ResponseEntity<Results> responseEntity =
          restTemplate.exchange(pathAndQuery, HttpMethod.GET, null, Results.class);
      return retrieveResult(responseEntity);

    } catch (HttpStatusCodeException httpStatusCodeException) {
      String message = httpStatusCodeException.getResponseBodyAsString();
      HttpStatus statusCode = httpStatusCodeException.getStatusCode();
      LOGGER.warn("Received HTTP status code {} and the following message: {}", statusCode,
          message);

      if (HttpStatus.SERVICE_UNAVAILABLE.equals(statusCode)) {
        throw new TooManyRequestsRuntimeException(message);
      }

      throw new SpringReactorUserLoaderRuntimeException(
          "Something wrong happened while consuming Random User API.");
    }
  }

  private List<User> retrieveResult(ResponseEntity<Results> responseEntity) {
    HttpStatus httpStatus = responseEntity.getStatusCode();

    if (HttpStatus.OK.equals(httpStatus)) {
      return responseEntity.getBody().getUsers();
    } else {
      LOGGER.warn("Ignoring result. HTTP status received: {}", httpStatus);
    }

    return Collections.emptyList();
  }

  private String elaboratePathAndQuery(int quantity) {

    Map<String, String> propertiesMap = new HashMap<>();
    propertiesMap.put(RESULTS_PROPERTY_NAME, Integer.toString(quantity));
    StringSubstitutor stringSubstitutor = new StringSubstitutor(propertiesMap);
    return stringSubstitutor.replace(RANDOM_USER_PATH_AND_QUERY_TEMPLATE);
  }
}
