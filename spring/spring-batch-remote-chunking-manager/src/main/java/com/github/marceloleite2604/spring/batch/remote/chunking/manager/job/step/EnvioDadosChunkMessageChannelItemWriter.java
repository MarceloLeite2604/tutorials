package com.github.marceloleite2604.spring.batch.remote.chunking.manager.job.step;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.batch.integration.chunk.ChunkMessageChannelItemWriter;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.spring.batch.remote.chunking.manager.configuration.NomesBeans;

@Component
public class EnvioDadosChunkMessageChannelItemWriter extends ChunkMessageChannelItemWriter<String> {

	@Inject
	@Named(NomesBeans.MESSAGE_CHANNEL_REQUESTS)
	private MessageChannel messageChannelRequests;

	@Inject
	@Named(NomesBeans.POLLABLE_CHANNEL_RESPONSES)
	PollableChannel pollableChannelResponses;

	@PostConstruct
	public void postConstruct() {
		MessagingTemplate messagingTemplate = new MessagingTemplate(messageChannelRequests);
		setMessagingOperations(messagingTemplate);
		setReplyChannel(pollableChannelResponses);
	}

}
