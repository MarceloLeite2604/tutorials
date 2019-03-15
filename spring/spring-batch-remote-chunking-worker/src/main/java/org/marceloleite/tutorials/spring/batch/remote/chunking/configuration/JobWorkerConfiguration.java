package org.marceloleite.tutorials.spring.batch.remote.chunking.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkProcessorChunkHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

@Configuration
@EnableIntegration
public class JobWorkerConfiguration {

	public ItemProcessor<Integer, Integer> itemProcessor() {
		return item -> {
			System.out.println("processing item " + item);
			return item;
		};
	}

	public ItemWriter<Integer> itemWriter() {
		return items -> {
			for (Integer item : items) {
				System.out.println("writing item " + item);
			}
		};
	}
	
	@Bean(NomesBeans.ACTIVE_MQ_CONNECTION_FACTORY)
	public ActiveMQConnectionFactory criarActiveMQConnectionFactory() {
		return new ActiveMQConnectionFactory();
	}
	
	@Bean(NomesBeans.INTEGRATION_FLOW_ENTRADA_REQUISICOES)
	public IntegrationFlow criarIntegracaoFlowEntradaRequisicoes(ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows
				.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory).destination("remote-chunking-requisicoes"))
				.channel(new DirectChannel())
				.get();
	}
	
	@Bean(NomesBeans.INTEGRATION_FLOW_SAIDA_RESPOSTAS)
	public IntegrationFlow criarIntegrationFlowSaidaRespostas(ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows
				.from(new DirectChannel())
				.handle(Jms.outboundAdapter(jmsConnectionFactory).destination("remote-chunking-respostas"))
				.get();
	}
	
	@Bean
	@ServiceActivator(inputChannel = "remote-chunking-requisicoes", outputChannel = "remote-chunking-respostas")
	public ChunkProcessorChunkHandler<Integer> chunkProcessorChunkHandler() {
		ChunkProcessor<Integer> chunkProcessor = new SimpleChunkProcessor<>(itemProcessor(), itemWriter());
		ChunkProcessorChunkHandler<Integer> chunkProcessorChunkHandler = new ChunkProcessorChunkHandler<>();
		chunkProcessorChunkHandler.setChunkProcessor(chunkProcessor);
		return chunkProcessorChunkHandler;
	}
}
