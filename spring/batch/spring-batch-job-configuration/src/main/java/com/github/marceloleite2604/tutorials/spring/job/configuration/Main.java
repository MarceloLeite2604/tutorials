package com.github.marceloleite2604.tutorials.spring.job.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosProperties;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios.AquisicaoUsuariosProperties;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;

@SpringBootApplication
@EnableConfigurationProperties({ CriadorUsuariosProperties.class, AquisicaoUsuariosProperties.class,
		PersistenciaUsuariosProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
