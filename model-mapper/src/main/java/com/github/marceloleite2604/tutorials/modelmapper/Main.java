package com.github.marceloleite2604.tutorials.modelmapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.marceloleite2604.tutorials.modelmapper.properties.DatabaseProperties;
import com.github.marceloleite2604.tutorials.modelmapper.properties.EncryptionProperties;

@SpringBootApplication
@EnableConfigurationProperties({ DatabaseProperties.class, EncryptionProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
