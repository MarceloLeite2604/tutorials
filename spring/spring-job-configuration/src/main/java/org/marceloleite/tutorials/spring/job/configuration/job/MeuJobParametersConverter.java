package org.marceloleite.tutorials.spring.job.configuration.job;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.stereotype.Component;

@Component
public class MeuJobParametersConverter implements JobParametersConverter {

	private static final String PREFIXO_PARAMETRO = "--";

	private static final String PARAMETRO_INSTANTE = "instante";

	@Override
	public JobParameters getJobParameters(Properties properties) {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

		String instante = Optional.ofNullable(properties)
				.map(p -> (String) p.getOrDefault(adicionarPrefixoParametro(PARAMETRO_INSTANTE),
						obterValorPadraoInstante()))
				.orElse(obterValorPadraoInstante());

		jobParametersBuilder.addString(PARAMETRO_INSTANTE, instante, true);
		return jobParametersBuilder.toJobParameters();
	}

	private String obterValorPadraoInstante() {
		LocalDateTime localDateTime = LocalDateTime.now();

		int minute = localDateTime.getMinute();
		minute -= minute % 10;

		return localDateTime.withMinute(minute)
				.withSecond(0)
				.withNano(0)
				.toString();
	}

	private String adicionarPrefixoParametro(String nomeParametro) {
		return PREFIXO_PARAMETRO + nomeParametro;
	}

	@Override
	public Properties getProperties(JobParameters jobParameters) {
		Properties properties = new Properties();
		String instante = jobParameters.getString(PARAMETRO_INSTANTE);
		properties.setProperty(PARAMETRO_INSTANTE, instante);
		return properties;
	}

}
