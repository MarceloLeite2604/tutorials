package com.github.marceloleite2604.tutorials.service;

import com.github.marceloleite2604.tutorials.model.Food;
import com.github.marceloleite2604.tutorials.repository.FoodRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class FoodService {

  private final FoodRepository foodRepository;

  public FoodService(FoodRepository foodRepository) {
    this.foodRepository = foodRepository;
  }

  @GraphQLQuery(name = "foods")
  public List<Food> getFoods() {
    return foodRepository.findAll();
  }

  @GraphQLQuery(name = "food")
  public Optional<Food> getFoodById(@GraphQLArgument(name = "id") Long id) {
    return foodRepository.findById(id);
  }

  @GraphQLMutation(name = "saveFood")
  public Food saveFood(@GraphQLArgument(name = "food") Food food) {
    return foodRepository.save(food);
  }

  @GraphQLMutation(name = "deleteFood")
  public void deleteFood(@GraphQLArgument(name = "id") Long id) {
    foodRepository.deleteById(id);
  }

  @GraphQLQuery(name = "isGood")
  public boolean isGood(@GraphQLContext Food food) {
    return !Arrays.asList("Avocado", "Spam").contains(food.getName());
  }

}
