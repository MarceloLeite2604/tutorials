package com.github.marceloleite2604.spring.batch.remote.chunking.job.step;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class EnvioDadosJobExecutionListener implements JobExecutionListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EnvioDadosJobExecutionListener.class);

	@Inject
	private List<MessageListenerContainer> listMessageListenerContainer;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// Não utilizado.
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		LOGGER.info("Encerrando as conexões.");
		listMessageListenerContainer.forEach(MessageListenerContainer::stop);
		// listMessageListenerContainer.get(0).
		LOGGER.info("Conexões encerradas.");
	}

}
