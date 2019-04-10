package com.github.marceloleite2604.webvalidations.dao;

import com.github.marceloleite2604.webvalidations.model.Greeting;
import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

}
