package com.github.marceloleite2604.spring.batch.remote.chunking.worker.configuration;

import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkProcessorChunkHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

import com.github.marceloleite2604.spring.batch.remote.chunking.worker.job.step.RecebimentoDadosItemProcessor;
import com.github.marceloleite2604.spring.batch.remote.chunking.worker.job.step.RecebimentoDadosItemWriter;

@Configuration
@EnableIntegration
public class JobConfiguration {

	@Bean
	@ServiceActivator(
			inputChannel = NomesBeans.MESSAGE_CHANNEL_REQUESTS,
			outputChannel = NomesBeans.MESSAGE_CHANNEL_RESPONSES)
	public ChunkProcessorChunkHandler<String> chunkProcessorChunkHandler(
			RecebimentoDadosItemProcessor recebimentoDadosItemProcessor,
			RecebimentoDadosItemWriter recebimentoDadosItemWriter) {
		ChunkProcessor<String> chunkProcessor = new SimpleChunkProcessor<>(
				recebimentoDadosItemProcessor, recebimentoDadosItemWriter);
		ChunkProcessorChunkHandler<String> chunkProcessorChunkHandler = new ChunkProcessorChunkHandler<>();
		chunkProcessorChunkHandler.setChunkProcessor(chunkProcessor);
		return chunkProcessorChunkHandler;
	}
}
