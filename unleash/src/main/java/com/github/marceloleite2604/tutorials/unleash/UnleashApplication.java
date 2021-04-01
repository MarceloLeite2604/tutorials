package com.github.marceloleite2604.tutorials.unleash;

import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.util.UnleashConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnleashApplication {

    @Bean("unleash")
    public Unleash createUnleash() {
        UnleashConfig config = UnleashConfig.builder()
            .appName("com.github.marceloleite2604.tutorials.unleash")
            .instanceId("instance 1")
            .unleashAPI("http://localhost:4242/api/")
            .build();

        return new DefaultUnleash(config);
    }

    @Bean("commandLineRunner")
    public CommandLineRunner createCommandLineRunner(Unleash unleash) {
        return args -> {
            while(true) {
                boolean enabled = unleash.isEnabled("my-feature-toggle");
                System.out.println("Feature \"my-feature-toggle\" is " + (enabled ? "enabled" : "disabled") + ".");
                Thread.sleep(1000);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(UnleashApplication.class, args);
    }

}
