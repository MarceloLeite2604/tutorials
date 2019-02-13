package org.marceloleite.tutorials.spring.job.configuration;

import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobProperties;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesProperties;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ MeuJobProperties.class, ObterClientesProperties.class,
		PersistirClientesProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
