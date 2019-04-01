package org.marceloleite.tutorials.spring.batch.remote.chunking.configuration;

import javax.inject.Named;

import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkProcessorChunkHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
public class JobWorkerConfiguration {

	@Bean("itemProcessor")
	public ItemProcessor<String, String> criarItemProcessor() {
		return item -> {
			System.out.println("processing item " + item);
			return item;
		};
	}

	@Bean("itemWriter")
	public ItemWriter<String> criarItemWriter() {
		return items -> {
			for (String item : items) {
				System.out.println("writing item " + item);
			}
		};
	}

	@Bean
	@ServiceActivator(inputChannel = "requests", outputChannel = "responses")
	public ChunkProcessorChunkHandler<String> chunkProcessorChunkHandler(
			@Named("itemProcessor") ItemProcessor<String, String> itemProcessor,
			@Named("itemWriter") ItemWriter<String> itemWriter) {
		ChunkProcessor<String> chunkProcessor = new SimpleChunkProcessor<>(itemProcessor,
				itemWriter);
		ChunkProcessorChunkHandler<String> chunkProcessorChunkHandler = new ChunkProcessorChunkHandler<>();
		chunkProcessorChunkHandler.setChunkProcessor(chunkProcessor);
		return chunkProcessorChunkHandler;
	}
}
