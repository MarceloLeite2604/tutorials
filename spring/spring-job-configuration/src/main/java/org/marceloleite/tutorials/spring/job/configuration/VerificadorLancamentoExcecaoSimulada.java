package org.marceloleite.tutorials.spring.job.configuration;

import java.util.Random;

public class VerificadorLancamentoExcecaoSimulada {

	private double percentualChance;
	
	private Random random;
	
	public VerificadorLancamentoExcecaoSimulada(double percentualChance) {
		this.percentualChance = percentualChance;
		this.random = new Random();
	}
	
	public void verificar() {
		if (random.nextDouble() <= percentualChance) {
			throw new RuntimeException("Exceção simulada.");
		}
	}
}
