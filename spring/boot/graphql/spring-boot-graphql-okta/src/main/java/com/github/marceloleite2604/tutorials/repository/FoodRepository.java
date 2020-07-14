package com.github.marceloleite2604.tutorials.repository;

import com.github.marceloleite2604.tutorials.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
