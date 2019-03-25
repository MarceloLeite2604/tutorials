package com.github.marceloleite2604.tutorials.spring.integration;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

public class ListarArquivoChannelInterceptor implements ChannelInterceptor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ListarArquivoChannelInterceptor.class);

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		if (message.getPayload() instanceof File) {
			File arquivo = (File) message.getPayload();

			LOGGER.info("Recebido o arquivo: {}.", arquivo.getName());
		}
		return ChannelInterceptor.super.postReceive(message, channel);
	}
}
