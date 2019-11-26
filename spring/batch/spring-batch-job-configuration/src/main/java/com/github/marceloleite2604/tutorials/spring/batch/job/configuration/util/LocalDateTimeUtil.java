package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeUtil {

	private static final DateTimeFormatter DATE_TIME_FORMATTER_NOME_DE_ARQUIVO = DateTimeFormatter
			.ofPattern("yyyy-MM-dd-kk-mm");

	public final String formatarParaNomeDeArquivo(LocalDateTime localDateTime) {
		return localDateTime.format(DATE_TIME_FORMATTER_NOME_DE_ARQUIVO);
	}

	public LocalDateTime parseDataParaNomeArquivo(String texto) {
		return LocalDateTime.parse(texto, DATE_TIME_FORMATTER_NOME_DE_ARQUIVO);
	}

}
