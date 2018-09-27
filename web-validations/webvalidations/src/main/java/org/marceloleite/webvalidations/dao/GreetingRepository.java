package org.marceloleite.webvalidations.dao;

import org.marceloleite.webvalidations.model.Greeting;
import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

}
