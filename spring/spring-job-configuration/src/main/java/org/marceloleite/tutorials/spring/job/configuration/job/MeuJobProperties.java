package org.marceloleite.tutorials.spring.job.configuration.job;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesProperties;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("meu-job")
public class MeuJobProperties {

	private String caminhoArquivoDeUsuarios;

	@Inject
	private ObterClientesProperties obterClientesProperties;

	@Inject
	private PersistirClientesProperties persistirClientesProperties;

	public String getCaminhoArquivoDeUsuarios() {
		return caminhoArquivoDeUsuarios;
	}

	public void setCaminhoArquivoDeUsuarios(String caminhoArquivoDeUsuarios) {
		this.caminhoArquivoDeUsuarios = caminhoArquivoDeUsuarios;
	}

	public ObterClientesProperties getObterClientesProperties() {
		return obterClientesProperties;
	}

	public void setObterClientesProperties(ObterClientesProperties obterClientesProperties) {
		this.obterClientesProperties = obterClientesProperties;
	}

	public PersistirClientesProperties getPersistirClientesProperties() {
		return persistirClientesProperties;
	}

	public void setPersistirClientesProperties(
			PersistirClientesProperties persistirClientesProperties) {
		this.persistirClientesProperties = persistirClientesProperties;
	}
}
