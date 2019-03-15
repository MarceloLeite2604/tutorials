package org.marceloleite.tutorials.spring.batch.remote.chunking.configuration;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.integration.chunk.ChunkMessageChannelItemWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.scheduling.PollerMetadata;

@Configuration
@EnableBatchProcessing
@EnableIntegration
public class JobConfiguration extends DefaultBatchConfigurer {

	private QueueChannel queueChannel = new QueueChannel();
	
	private DirectChannel directChannel = new DirectChannel();

	@Bean(NomesBeans.ACTIVE_MQ_CONNECTION_FACTORY)
	public ActiveMQConnectionFactory criarActiveMQConnectionFactory() {
		return new ActiveMQConnectionFactory();
	}

	@Bean(NomesBeans.INTEGRATION_FLOW_SAIDA_REQUISICOES)
	public IntegrationFlow criarIntegrationFlowSaidaRequisicoes(
			ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows.from(queueChannel)
				.handle(Jms.outboundAdapter(jmsConnectionFactory)
						.destination("remote-chunking-requisicoes"))
				.get();
	}

	@Bean(NomesBeans.INTEGRATION_FLOW_ENTRADA_RESPOSTAS)
	public IntegrationFlow criarIntegrationFlowEntradaRespostas(
			ActiveMQConnectionFactory jmsConnectionFactory) {
		
		return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory)
				.destination("remote-chunking-respostas"))
				.channel(queueChannel)
				.get();
	}

	@Bean(name = { "defaultPoller", PollerMetadata.DEFAULT_POLLER })
	public PollerMetadata poolerMetadata() {
		return Pollers.fixedDelay(1000)
				.taskExecutor(Executors.newCachedThreadPool())
				.get();
	}
	
	@Bean
	public ItemWriter<String> itemWriter() {
		MessagingTemplate messagingTemplate = new MessagingTemplate();
		messagingTemplate.setDefaultChannel(directChannel);
		ChunkMessageChannelItemWriter<String> chunkMessageChannelItemWriter = new ChunkMessageChannelItemWriter<>();
		chunkMessageChannelItemWriter.setMessagingOperations(messagingTemplate);
		chunkMessageChannelItemWriter.setReplyChannel(queueChannel);
		return chunkMessageChannelItemWriter;
	}

	@Bean
	public Job criarJobRemoteChunking(JobBuilderFactory jobBuilderFactory,
			@Named(NomesBeans.STEP_ENVIO_DADOS) Step stepEnvioDados) {
		return jobBuilderFactory.get("remote-chunking-job")
				.start(stepEnvioDados)
				.build();
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		setDataSource(null);
	}
}
