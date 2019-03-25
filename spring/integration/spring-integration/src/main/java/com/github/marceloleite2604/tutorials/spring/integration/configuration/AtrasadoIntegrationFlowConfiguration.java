package com.github.marceloleite2604.tutorials.spring.integration.configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.GenericEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.BridgeHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.github.marceloleite2604.tutorials.spring.integration.NomesBeans;

@Configuration
@ConditionalOnExpression("#{'${programa.tipo-fluxo-integracao:normal}' == 'atrasado'}")
public class AtrasadoIntegrationFlowConfiguration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AtrasadoIntegrationFlowConfiguration.class);

	@Bean(NomesBeans.MESSAGE_CHANNEL_PARA_ATRASO)
	public MessageChannel criarMessageChannelParaAtraso() {
		return new QueueChannel(2);
	}

	@Bean(NomesBeans.INTEGRATION_FLOW_ENTRADA_PARA_CANAL_ATRASO)
	public IntegrationFlow criarIntegrationFlowEntradaParaCanalAtraso(
			@Named(NomesBeans.MESSAGE_SOURCE_DIRETORIO_ENTRADA) MessageSource<File> messageSourceDiretorioEntrada,
			@Named(NomesBeans.GENERIC_SELECTOR_SOMENTE_JPEGS) GenericSelector<File> genericSelectorSomenteJpegs,
			@Named(NomesBeans.MESSAGE_CHANNEL_PARA_ATRASO) MessageChannel messageChannelParaAtraso) {

		LOGGER.info("Criando integration flow de entrega atrasada.");

		return IntegrationFlows.from(messageSourceDiretorioEntrada)
				.filter(genericSelectorSomenteJpegs)
				.channel(messageChannelParaAtraso)
				.get();
	}

	@Bean(NomesBeans.INTEGRATION_FLOW_CANAL_ATRASO_PARA_SAIDA)
	public IntegrationFlow criarIntegrationFlowCanalAtrasoParaSaida(
			@Named(NomesBeans.MESSAGE_CHANNEL_PARA_ATRASO) MessageChannel messageChannelParaAtraso,
			@Named(NomesBeans.GENERIC_SELECTOR_SOMENTE_JPEGS) GenericSelector<File> genericSelectorSomenteJpegs,
			@Named(NomesBeans.MESSAGE_HANDLER_DIRETORIO_SAIDA) MessageHandler messageHandlerDiretorioSaida) {
		return IntegrationFlows.from(messageChannelParaAtraso)
				.bridge(criarConsumerGenericEndpointSpecBridgeHandler())
				.log(message -> "Recebido o arquivo \"" + ((File) message.getPayload()).getName()
						+ "\".")
				.handle(messageHandlerDiretorioSaida)
				.get();
	}

	private Consumer<GenericEndpointSpec<BridgeHandler>> criarConsumerGenericEndpointSpecBridgeHandler() {
		return (endpoint -> endpoint.poller(Pollers.fixedRate(1, TimeUnit.SECONDS, 3)));
	}

}
