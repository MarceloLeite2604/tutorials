package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job;

import javax.inject.Inject;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.CaminhosPropriedades;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.aquisicaousuarios.AquisicaoUsuariosProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;

@ConfigurationProperties(CaminhosPropriedades.CRIADOR_USUARIOS)
public class CriadorUsuariosProperties {

	private String diretorioArquivoDeUsuarios;

	@Inject
	private AquisicaoUsuariosProperties obterClientesProperties;

	@Inject
	private PersistenciaUsuariosProperties persistirClientesProperties;

	public String getDiretorioArquivoDeUsuarios() {
		return diretorioArquivoDeUsuarios;
	}

	public void setDiretorioArquivoDeUsuarios(String diretorioArquivoDeUsuarios) {
		this.diretorioArquivoDeUsuarios = diretorioArquivoDeUsuarios;
	}

	public AquisicaoUsuariosProperties getObterClientesProperties() {
		return obterClientesProperties;
	}

	public void setObterClientesProperties(AquisicaoUsuariosProperties obterClientesProperties) {
		this.obterClientesProperties = obterClientesProperties;
	}

	public PersistenciaUsuariosProperties getPersistirClientesProperties() {
		return persistirClientesProperties;
	}

	public void setPersistirClientesProperties(
			PersistenciaUsuariosProperties persistirClientesProperties) {
		this.persistirClientesProperties = persistirClientesProperties;
	}
}
