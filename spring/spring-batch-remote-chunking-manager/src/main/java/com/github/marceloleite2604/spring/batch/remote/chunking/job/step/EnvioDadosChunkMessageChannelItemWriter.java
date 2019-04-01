package com.github.marceloleite2604.spring.batch.remote.chunking.job.step;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.batch.integration.chunk.ChunkMessageChannelItemWriter;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

@Component
public class EnvioDadosChunkMessageChannelItemWriter extends ChunkMessageChannelItemWriter<String> {

	@Inject
	@Named("requests")
	private MessageChannel messageChannelRequests;

	@Inject
	@Named("responses")
	PollableChannel pollableChannelResponses;

	@PostConstruct
	public void postConstruct() {
		MessagingTemplate messagingTemplate = new MessagingTemplate(messageChannelRequests);
		setMessagingOperations(messagingTemplate);
		setReplyChannel(pollableChannelResponses);
	}

}
