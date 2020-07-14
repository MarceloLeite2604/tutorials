package com.github.marceloleite2604.tutorials.configuration;

import com.github.marceloleite2604.tutorials.model.Food;
import com.github.marceloleite2604.tutorials.service.FoodService;
import java.util.stream.Stream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean("initApplicationRunner")
  ApplicationRunner createInitApplicationRunner(FoodService foodService) {
    return args -> {
      Stream.of("Pizza", "Spam", "Eggs", "Avocado").forEach(name -> {
        Food food = new Food();
        food.setName(name);
        foodService.saveFood(food);
      });
      foodService.getFoods().forEach(System.out::println);
    };
  }

}
