package com.github.marceloleite2604.spring.batch.remote.chunking.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.jms.dsl.JmsOutboundChannelAdapterSpec.JmsOutboundChannelSpecTemplateAware;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableJms
// @EnableBinding(RemoteChunkingProcessor.class)
public class IntegrationConfiguration {

	@Bean("requests")
	public MessageChannel criarMessageChannelRequests() {
		return new DirectChannel();
	}

	@Bean("responses")
	public QueueChannel criarMessageChannelResponses() {
		return new QueueChannel();
	}

	@Bean("integrationFlowRequests")
	public StandardIntegrationFlow criarIntegrationFlowRequests(
			ActiveMQConnectionFactory activeMQConnectionFactory,
			@Named("requests") MessageChannel messageChannelRequests) {
		JmsOutboundChannelSpecTemplateAware destination = Jms
				.outboundAdapter(activeMQConnectionFactory)
				.destination("activemq-requests");
		destination.stop();
		return IntegrationFlows.from(messageChannelRequests)
				.handle(destination)
				.get();
	}

	@Bean("listMessageListenerContainer")
	public List<MessageListenerContainer> criarListMessageListenerContainer() {
		return new ArrayList<>();
	}

	@Bean("integrationFlowResponses")
	public StandardIntegrationFlow criarIntegrationFlowResponses(
			ActiveMQConnectionFactory actimeMqConnectionFactory,
			@Named("responses") QueueChannel pollableChannelResponses,
			List<MessageListenerContainer> listMessageListenerContainer) {

		StandardIntegrationFlow standardIntegrationFlow;
		return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(actimeMqConnectionFactory)
				.destination("activemq-responses")
				.configureListenerContainer(jmsDefaultListenerContainerSpec -> {
					DefaultMessageListenerContainer defaultMessageListenerContainer = jmsDefaultListenerContainerSpec
							.get();
					listMessageListenerContainer.add(defaultMessageListenerContainer);
				}))
				.channel(pollableChannelResponses)
				.get();
	}
}
