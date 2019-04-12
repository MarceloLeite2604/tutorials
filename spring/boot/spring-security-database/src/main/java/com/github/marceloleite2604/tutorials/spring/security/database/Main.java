package com.github.marceloleite2604.tutorials.spring.security.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.marceloleite2604.tutorials.spring.security.database.properties.ProgramaBancoProperties;

@SpringBootApplication
@EnableConfigurationProperties({ProgramaBancoProperties.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
