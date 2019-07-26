package com.github.marceloleite2604.tutorials.modelmapper.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	@Bean(BeanNames.MODEL_MAPPER)
	public ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setSkipNullEnabled(true);

		modelMapper.validate();

		return modelMapper;
	}
}
