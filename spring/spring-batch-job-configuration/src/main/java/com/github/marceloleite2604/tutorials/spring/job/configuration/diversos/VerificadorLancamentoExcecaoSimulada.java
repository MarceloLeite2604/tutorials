package com.github.marceloleite2604.tutorials.spring.job.configuration.diversos;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;

@Component
public class VerificadorLancamentoExcecaoSimulada {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VerificadorLancamentoExcecaoSimulada.class);

	@Value("${criador-usuarios.fator-chance-lancamento-excecao}")
	private double fatorChanceLancamentoExcecao;

	private Random random;

	@PostConstruct
	public void postConstruct() {
		LOGGER.info("Criado o verificador de lançamento de exceção simulada com fator de {}.",
				fatorChanceLancamentoExcecao);
		this.random = new Random();
	}

	public void verificar() {
		if (random.nextDouble() > (1.0 - fatorChanceLancamentoExcecao)) {
			throw new SpringBatchJobConfigurationRuntimeException("Exceção simulada.");
		}
	}
}
