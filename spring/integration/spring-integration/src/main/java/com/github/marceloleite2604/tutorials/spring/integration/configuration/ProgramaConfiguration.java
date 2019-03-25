package com.github.marceloleite2604.tutorials.spring.integration.configuration;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.github.marceloleite2604.tutorials.spring.integration.NomesBeans;

@Configuration
public class ProgramaConfiguration {

	private static final String EXTENSAO_ARQUIVOS_JPG = ".jpg";

	public static final String CAMINHO_DIRETORIO_ENTRADA = "arquivos/entrada";

	public static final String CAMINHO_DIRETORIO_SAIDA = "arquivos/saida";

	@Bean(PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata criarDefaultPooler() {

		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new PeriodicTrigger(1000));
		return pollerMetadata;
	}

	@Bean(NomesBeans.GENERIC_SELECTOR_SOMENTE_JPEGS)
	public GenericSelector<File> criarGenericSelectorSomenteJpegs() {
		return (file -> file.getName()
				.endsWith(EXTENSAO_ARQUIVOS_JPG));
	}

	@Bean(NomesBeans.MESSAGE_SOURCE_DIRETORIO_ENTRADA)
	public MessageSource<File> criarMessageSourceDiretorioEntrada() {
		FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
		fileReadingMessageSource.setDirectory(new File(CAMINHO_DIRETORIO_ENTRADA));
		return fileReadingMessageSource;
	}

	@Bean(NomesBeans.MESSAGE_HANDLER_DIRETORIO_SAIDA)
	public MessageHandler criarMessageHandlerDiretorioSaida() {
		FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(
				new File(CAMINHO_DIRETORIO_SAIDA));
		fileWritingMessageHandler.setExpectReply(false);
		return fileWritingMessageHandler;
	}

}
