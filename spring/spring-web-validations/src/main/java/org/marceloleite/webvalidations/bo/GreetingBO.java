package org.marceloleite.webvalidations.bo;

import javax.inject.Inject;

import org.marceloleite.webvalidations.dao.GreetingRepository;
import org.marceloleite.webvalidations.model.Greeting;
import org.marceloleite.webvalidations.model.validation.database.GreetingDatabaseValidation;
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
