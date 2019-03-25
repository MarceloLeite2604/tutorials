package com.github.marceloleite2604.tutorials.spring.integration;

import java.util.function.Consumer;

import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;

public final class IntegrationFlowUtils {
	
	private IntegrationFlowUtils() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}
	
	public static Consumer<SourcePollingChannelAdapterSpec> criarEndpointConfigurerPadrao() {
		return (configurer -> configurer.poller(Pollers.fixedDelay(200)));
	}

	

}
