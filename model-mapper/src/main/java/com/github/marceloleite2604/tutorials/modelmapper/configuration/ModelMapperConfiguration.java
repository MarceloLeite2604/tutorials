package com.github.marceloleite2604.tutorials.modelmapper.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.propertymap.UserDtoToUserPoPropertyMapper;

@Configuration
public class ModelMapperConfiguration {

	@Bean(BeanNames.MODEL_MAPPER)
	public ModelMapper createModelMapper(UserDtoToUserPoPropertyMapper userDtoToUserPoPropertyMapper) {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setSkipNullEnabled(true);
		
		modelMapper.addMappings(userDtoToUserPoPropertyMapper);

		modelMapper.validate();

		return modelMapper;
	}
}
