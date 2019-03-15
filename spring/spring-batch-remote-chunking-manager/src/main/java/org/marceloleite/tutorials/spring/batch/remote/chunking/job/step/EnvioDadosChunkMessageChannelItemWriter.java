package org.marceloleite.tutorials.spring.batch.remote.chunking.job.step;

import org.springframework.batch.integration.chunk.ChunkMessageChannelItemWriter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class EnvioDadosChunkMessageChannelItemWriter extends ChunkMessageChannelItemWriter<String> {

	public EnvioDadosChunkMessageChannelItemWriter() {
		MessageChannel messageChannel = new DirectChannel();
		MessagingTemplate messagingTemplate = new MessagingTemplate(messageChannel);
		setMessagingOperations(messagingTemplate);
		setReplyChannel(new QueueChannel());
	}
}
