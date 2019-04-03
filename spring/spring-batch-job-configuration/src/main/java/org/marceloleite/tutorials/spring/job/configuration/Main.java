package org.marceloleite.tutorials.spring.job.configuration;

import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosProperties;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios.AquisicaoUsuariosProperties;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ CriadorUsuariosProperties.class, AquisicaoUsuariosProperties.class,
		PersistenciaUsuariosProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
