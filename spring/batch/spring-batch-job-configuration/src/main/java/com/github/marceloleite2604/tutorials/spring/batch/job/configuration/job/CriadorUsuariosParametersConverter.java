package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.enumeration.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.LocalDateTimeUtil;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import javax.inject.Inject;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.stereotype.Component;

@Component
public class CriadorUsuariosParametersConverter implements JobParametersConverter {

	private static final int PERIODO_MINUTOS = 20;
	
	@Inject
	private LocalDateTimeUtil localDateTimeUtil;

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
		minute -= minute % PERIODO_MINUTOS;

		return localDateTimeUtil.formatarParaNomeDeArquivo(localDateTime.withMinute(minute)
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
