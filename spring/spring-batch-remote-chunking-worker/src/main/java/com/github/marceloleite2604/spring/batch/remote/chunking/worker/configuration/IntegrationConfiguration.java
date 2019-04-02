package com.github.marceloleite2604.spring.batch.remote.chunking.worker.configuration;

import javax.inject.Named;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfiguration {

	private static final String NOME_CANAL_ACTIVEMQ_REQUESTS = "activemq-requests";

	private static final String NOME_CANAL_ACTIVEMQ_RESPONSES = "activemq-responses";

	@Bean(NomesBeans.MESSAGE_CHANNEL_REQUESTS)
	public MessageChannel criarMessageChannelRequests() {
		return new DirectChannel();
	}

	@Bean(NomesBeans.MESSAGE_CHANNEL_RESPONSES)
	public MessageChannel criarMessageChannelResponses() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow criarIntegrationFlowResponses(
			ActiveMQConnectionFactory jmsConnectionFactory,
			@Named(NomesBeans.MESSAGE_CHANNEL_RESPONSES) MessageChannel messageChannelResponses) {
		return IntegrationFlows.from(messageChannelResponses)
				.handle(Jms.outboundAdapter(jmsConnectionFactory)
						.destination(NOME_CANAL_ACTIVEMQ_RESPONSES))
				.get();
	}

	@Bean
	public IntegrationFlow criarIntegrationFlowRequests(
			ActiveMQConnectionFactory jmsConnectionFactory,
			@Named(NomesBeans.MESSAGE_CHANNEL_REQUESTS) MessageChannel messageChannelRequests) {
		return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory)
				.destination(NOME_CANAL_ACTIVEMQ_REQUESTS))
				.channel(messageChannelRequests)
				.get();
	}
}
