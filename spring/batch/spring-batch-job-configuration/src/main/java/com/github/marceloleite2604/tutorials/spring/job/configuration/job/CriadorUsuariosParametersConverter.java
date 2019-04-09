package com.github.marceloleite2604.tutorials.spring.job.configuration.job;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.LocalDateTimeUtil;

@Component
public class CriadorUsuariosParametersConverter implements JobParametersConverter {

	@Override
	public JobParameters getJobParameters(Properties properties) {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

		String instante = Optional.ofNullable(properties)
				.map(p -> (String) p.getOrDefault(
						CriadorUsuariosContextoPropriedade.INSTANTE.getNomeCompleto(),
						obterValorPadraoInstante()))
				.orElse(obterValorPadraoInstante());

		jobParametersBuilder.addString(CriadorUsuariosContextoPropriedade.INSTANTE.getNome(),
				instante, true);
		return jobParametersBuilder.toJobParameters();
	}

	private String obterValorPadraoInstante() {
		LocalDateTime localDateTime = LocalDateTime.now();

		int minute = localDateTime.getMinute();
		minute -= minute % 10;

		return LocalDateTimeUtil.formatarParaNomeDeArquivo(localDateTime.withMinute(minute)
				.withSecond(0)
				.withNano(0));
	}

	@Override
	public Properties getProperties(JobParameters jobParameters) {
		Properties properties = new Properties();
		String instante = jobParameters
				.getString(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());
		properties.setProperty(CriadorUsuariosContextoPropriedade.INSTANTE.getNome(), instante);
		return properties;
	}

}
