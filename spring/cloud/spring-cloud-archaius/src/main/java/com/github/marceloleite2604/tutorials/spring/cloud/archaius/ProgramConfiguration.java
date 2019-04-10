package com.github.marceloleite2604.tutorials.spring.cloud.archaius;

import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.config.sources.URLConfigurationSource;

@Configuration
public class ProgramConfiguration {

	@Bean
	public AbstractConfiguration createAbstractConfiguration() {
	    PolledConfigurationSource source = new URLConfigurationSource("classpath:other-source.properties");
	    return new DynamicConfiguration(source, new FixedDelayPollingScheduler());
	}
}
