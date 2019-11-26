package com.github.marceloleite2604.tutorials.spring.batch.job.configuration;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.AquisicaoUsuariosProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.CriadorUsuariosProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.CriptografiaProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.PersistenciaUsuariosProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ CriadorUsuariosProperties.class, AquisicaoUsuariosProperties.class,
		PersistenciaUsuariosProperties.class, CriptografiaProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
