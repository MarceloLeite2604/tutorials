package com.github.marceloleite2604.tutorials.spring.git.information;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramConfiguration {

	/*
	 * Não é necessário uma vez que o próprio Spring cria um objeto "GitProperties"
	 * com as informações do projeto.
	 */
	// @Bean("gitProperties")
	// @ConfigurationProperties("git")
	// public Properties criarGitProperties() {
	// return new Properties();
	// }

	/*
	 * Pode ser utilizado para ler as propriedades de um arquivo "properties" para
	 * mais tarde utilizar seus valores como substitutos através de EL.
	 */
	// @Bean
	// public static PropertySourcesPlaceholderConfigurer
	// criarPropertySourcesPlaceholderConfigurar() {
	// PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
	// new PropertySourcesPlaceholderConfigurer();
	// propertySourcesPlaceholderConfigurer.setLocation(new
	// ClassPathResource("git.properties"));
	// propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
	// propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
	// return propertySourcesPlaceholderConfigurer;
	// }
}
