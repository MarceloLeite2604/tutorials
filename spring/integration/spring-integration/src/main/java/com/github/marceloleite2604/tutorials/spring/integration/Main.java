package com.github.marceloleite2604.tutorials.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class Main {
	
	public static void main(String[] args) {
		ArquivoUtils.excluirArquivosJpgDiretorioSaida();
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Main.class, args);
		configurableApplicationContext.registerShutdownHook();
	}
}
