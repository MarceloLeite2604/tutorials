package com.github.marceloleite2604.tutorials.spring.integration.configuration;

import java.io.File;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageHandler;

import com.github.marceloleite2604.tutorials.spring.integration.IntegrationFlowUtils;
import com.github.marceloleite2604.tutorials.spring.integration.NomesBeans;

@Configuration
@ConditionalOnExpression("#{'${programa.tipo-fluxo-integracao:normal}' == 'normal'}")
public class NormalIntegrationFlowConfiguration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NormalIntegrationFlowConfiguration.class);

	@Bean
	public IntegrationFlow criarIntegrationFlow(
			@Named(NomesBeans.MESSAGE_SOURCE_DIRETORIO_ENTRADA) MessageSource<File> messageSourceDiretorioEntrada,
			@Named(NomesBeans.GENERIC_SELECTOR_SOMENTE_JPEGS) GenericSelector<File> genericSelectorSomenteJpegs,
			@Named(NomesBeans.MESSAGE_HANDLER_DIRETORIO_SAIDA) MessageHandler messageHandlerDiretorioSaida) {

		LOGGER.info("Criando integration flow normal.");

		return IntegrationFlows
				.from(messageSourceDiretorioEntrada,
						IntegrationFlowUtils.criarEndpointConfigurerPadrao())
				.filter(genericSelectorSomenteJpegs)
				.log(message -> "Recebido o arquivo \"" + ((File) message.getPayload()).getName()
						+ "\".")
				.handle(messageHandlerDiretorioSaida)
				.get();
	}
}
