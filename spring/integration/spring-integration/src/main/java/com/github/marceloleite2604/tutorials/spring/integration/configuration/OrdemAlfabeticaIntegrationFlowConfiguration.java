package com.github.marceloleite2604.tutorials.spring.integration.configuration;

import java.io.File;
import java.util.Comparator;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.github.marceloleite2604.tutorials.spring.integration.IntegrationFlowUtils;
import com.github.marceloleite2604.tutorials.spring.integration.ListarArquivoChannelInterceptor;
import com.github.marceloleite2604.tutorials.spring.integration.NomesBeans;

@Configuration
@ConditionalOnExpression("#{'${programa.tipo-fluxo-integracao:normal}' == 'ordem-alfabetica'}")
public class OrdemAlfabeticaIntegrationFlowConfiguration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrdemAlfabeticaIntegrationFlowConfiguration.class);

	@Bean(NomesBeans.MESSAGE_CHANNEL_ORDEM_ALFABETICA)
	public MessageChannel criarPriorityChannelOrdemAlfabetica() {
		PriorityChannel priorityChannel = new PriorityChannel(1000,
				criarComparatorFileOrdemAlfabetica());
		priorityChannel.addInterceptor(new ListarArquivoChannelInterceptor());
		return priorityChannel;
	}

	private Comparator<Message<?>> criarComparatorFileOrdemAlfabetica() {
		return ((arquivoUm, arquivoDois) -> ((File) arquivoUm.getPayload()).getName()
				.compareTo(((File) arquivoDois.getPayload()).getName()));
	}

	@Bean(NomesBeans.INTEGRATION_FLOW_ORDEM_ALFABETICA)
	public IntegrationFlow criarIntegrationFlow(
			@Named(NomesBeans.MESSAGE_SOURCE_DIRETORIO_ENTRADA) MessageSource<File> messageSourceDiretorioEntrada,
			@Named(NomesBeans.GENERIC_SELECTOR_SOMENTE_JPEGS) GenericSelector<File> genericSelectorSomenteJpegs,
			@Named(NomesBeans.MESSAGE_CHANNEL_ORDEM_ALFABETICA) MessageChannel messageChannelOrdemAlfabetica,
			@Named(NomesBeans.MESSAGE_HANDLER_DIRETORIO_SAIDA) MessageHandler messageHandlerDiretorioSaida) {

		LOGGER.info("Criando integration flow de ordem alfabética.");

		return IntegrationFlows
				.from(messageSourceDiretorioEntrada,
						IntegrationFlowUtils.criarEndpointConfigurerPadrao())
				.filter(genericSelectorSomenteJpegs)
				.channel(messageChannelOrdemAlfabetica)
				.handle(messageHandlerDiretorioSaida)
				.get();
	}

}
