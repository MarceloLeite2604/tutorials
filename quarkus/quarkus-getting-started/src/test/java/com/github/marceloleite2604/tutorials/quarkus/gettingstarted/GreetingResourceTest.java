package com.github.marceloleite2604.tutorials.quarkus.gettingstarted;

import io.quarkus.test.junit.QuarkusTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }
    
    @Test
    public void testGreetingEndpoint() {
    	String uuid = UUID.randomUUID().toString();
    	given()
        .when()
        .pathParam("name", uuid)
        .get("/hello/greeting/{name}")
        .then()
           .statusCode(200)
           .body(is("hello " + uuid));
    	
    }

}