package com.github.marceloleite2604.tutorials;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.okta.spring.boot.oauth.config.OktaOAuth2Properties;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(excludeName = {"AuthorityProvidersConfig"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTest {

  @MockBean
  private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(0)
  void listFoods() throws Exception {
    String expectedResponse = "{\"data\":{\"foods\":[" +
        "{\"id\":1,\"name\":\"Pizza\",\"isGood\":true}," +
        "{\"id\":2,\"name\":\"Spam\",\"isGood\":false}," +
        "{\"id\":3,\"name\":\"Eggs\",\"isGood\":true}," +
        "{\"id\":4,\"name\":\"Avocado\",\"isGood\":false}" +
        "]}}";

    mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
        .with(SecurityMockMvcRequestPostProcessors.jwt())
        .content("{\"query\":\"{ foods { id name isGood } }\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse))
        .andReturn();
  }

  @Test
  @Order(1)
  void addAndRemoveFood() throws Exception {
    String expectedResponseBefore = "{\"data\":{\"foods\":[" +
        "{\"id\":1,\"name\":\"Pizza\"}," +
        "{\"id\":2,\"name\":\"Spam\"}," +
        "{\"id\":3,\"name\":\"Eggs\"}," +
        "{\"id\":4,\"name\":\"Avocado\"}" +
        "]}}";
    String expectedResponseAfter = "{\"data\":{\"foods\":[" +
        "{\"id\":1,\"name\":\"Pizza\"}," +
        "{\"id\":2,\"name\":\"Spam\"}," +
        "{\"id\":3,\"name\":\"Eggs\"}," +
        "{\"id\":4,\"name\":\"Avocado\"}," +
        "{\"id\":5,\"name\":\"Pasta\"}" +
        "]}}";

    // List foods, expect 'New Food' to not be there
    mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
        .with(SecurityMockMvcRequestPostProcessors.jwt())
        .content("{\"query\":\"{ foods { id name } }\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponseBefore))
        .andReturn();

    // Add 'New Food'
    mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
        .with(SecurityMockMvcRequestPostProcessors.jwt())
        .content("{\"query\":\"mutation { saveFood(food: { name: \\\"Pasta\\\" }) { id name } }\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"data\":{\"saveFood\":{\"id\":5,\"name\":\"Pasta\"}}}"))
        .andReturn();

    // List foods, expect 'New Food' to be there
    mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
        .with(SecurityMockMvcRequestPostProcessors.jwt())
        .content("{\"query\":\"{ foods { id name } }\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponseAfter))
        .andReturn();

    // Remove 'New Food'
    mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
        .with(SecurityMockMvcRequestPostProcessors.jwt())
        .content("{\"query\":\"mutation { deleteFood(id: 5) }\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

    // List foods, expect 'New Food' to not be there
    mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
        .with(SecurityMockMvcRequestPostProcessors.jwt())
        .content("{\"query\":\"{ foods { id name } }\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponseBefore))
        .andReturn();
  }
}