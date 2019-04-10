package com.github.marceloleite2604.webvalidations.bo;

import javax.inject.Inject;

import com.github.marceloleite2604.webvalidations.dao.GreetingRepository;
import com.github.marceloleite2604.webvalidations.model.Greeting;
import com.github.marceloleite2604.webvalidations.model.validation.database.GreetingDatabaseValidation;
import org.springframework.stereotype.Component;

@Component
public class GreetingBO extends AbstractBO<Greeting> {

	@Inject
	private GreetingRepository greetingRepository;

	protected GreetingBO() {
		super(Greeting.class, GreetingDatabaseValidation.class);
	}

	public Greeting save(Greeting greeting) {
		validate(greeting);
		return greetingRepository.save(greeting);
	}
}
