package org.marceloleite.tutorials.spring.batch.remote.chunking.configuration;

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

	@Bean
	public ActiveMQConnectionFactory jmsConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://localhost:61616?daemon=true");
		connectionFactory.setTrustAllPackages(true);
		return connectionFactory;
	}

	@Bean("requests")
	public MessageChannel criarMessageChannelRequests() {
		return new DirectChannel();
	}

	@Bean("responses")
	public MessageChannel criarMessageChannelResponses() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow criarIntegrationFlowResponses(
			ActiveMQConnectionFactory jmsConnectionFactory,
			@Named("responses") MessageChannel messageChannelResponses) {
		return IntegrationFlows.from(messageChannelResponses)
				.handle(Jms.outboundAdapter(jmsConnectionFactory)
						.destination("activemq-responses"))
				.get();
	}

	@Bean
	public IntegrationFlow criarIntegrationFlowRequests(
			ActiveMQConnectionFactory jmsConnectionFactory,
			@Named("requests") MessageChannel messageChannelRequests) {
		return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory)
				.destination("activemq-requests"))
				.channel(messageChannelRequests)
				.get();
	}
}
