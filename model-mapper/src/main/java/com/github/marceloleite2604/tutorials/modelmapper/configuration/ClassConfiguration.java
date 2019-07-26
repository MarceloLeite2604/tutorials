package com.github.marceloleite2604.tutorials.modelmapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.modelmapper.controller.PageController;
import com.github.marceloleite2604.tutorials.modelmapper.controller.UserController;
import com.github.marceloleite2604.tutorials.modelmapper.model.ThymeleafModelAttributeNames;

@Configuration
public class ClassConfiguration {

	@Bean(BeanNames.PAGE_CONTROLLER_PATHS_CLASS)
	public Class<PageController.Paths> createPageControllerPathClass() {
		return PageController.Paths.class;
	}

	@Bean(BeanNames.USER_CONTROLLER_PATHS_CLASS)
	public Class<UserController.Paths> createUserControllerPathClass() {
		return UserController.Paths.class;
	}

	@Bean(BeanNames.THYMELEAF_MODEL_ATTRIBUTE_NAMES_CLASS)
	public Class<ThymeleafModelAttributeNames> createThymeleafModelAttributeNamesClass() {
		return ThymeleafModelAttributeNames.class;
	}
}
