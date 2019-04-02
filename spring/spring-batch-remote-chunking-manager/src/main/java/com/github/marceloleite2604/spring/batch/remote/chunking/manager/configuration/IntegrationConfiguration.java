package com.github.marceloleite2604.spring.batch.remote.chunking.manager.configuration;

import javax.inject.Named;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableJms
public class IntegrationConfiguration {

	private static final String NOME_CANAL_ACTIVEMQ_REQUESTS = "activemq-requests";

	private static final String NOME_CANAL_ACTIVEMQ_RESPONSES = "activemq-responses";

	@Bean(NomesBeans.MESSAGE_CHANNEL_REQUESTS)
	public MessageChannel criarMessageChannelRequests() {
		return new DirectChannel();
	}

	@Bean(NomesBeans.POLLABLE_CHANNEL_RESPONSES)
	public PollableChannel criarMessageChannelResponses() {
		return new QueueChannel();
	}

	@Bean
	public StandardIntegrationFlow criarIntegrationFlowRequests(
			ActiveMQConnectionFactory activeMQConnectionFactory,
			@Named(NomesBeans.MESSAGE_CHANNEL_REQUESTS) MessageChannel messageChannelRequests) {
		return IntegrationFlows.from(messageChannelRequests)
				.handle(Jms.outboundAdapter(activeMQConnectionFactory)
						.destination(NOME_CANAL_ACTIVEMQ_REQUESTS))
				.get();
	}

	@Bean
	public StandardIntegrationFlow criarIntegrationFlowResponses(
			ActiveMQConnectionFactory actimeMqConnectionFactory,
			@Named(NomesBeans.POLLABLE_CHANNEL_RESPONSES) PollableChannel pollableChannelResponses) {

		return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(actimeMqConnectionFactory)
				.destination(NOME_CANAL_ACTIVEMQ_RESPONSES))
				.channel(pollableChannelResponses)
				.get();
	}
}
