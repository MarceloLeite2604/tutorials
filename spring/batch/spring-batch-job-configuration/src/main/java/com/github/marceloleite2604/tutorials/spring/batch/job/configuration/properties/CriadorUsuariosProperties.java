package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.NomesBeans;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CaminhosProperties.CRIADOR_USUARIOS)
public class CriadorUsuariosProperties {

	@NotBlank
	private String diretorioArquivoDeUsuarios;

	@Min(1)
	private long totalDeRegistros;

	@Min(0)
	@Max(1)
	private double fatorChanceLancamentoExcecao;

	@Inject
	private CriptografiaProperties criptografiaProperties;

	@Inject
	private AquisicaoUsuariosProperties aquisicaoUsuariosProperties;

	@Inject
	private PersistenciaUsuariosProperties persistenciaUsuariosProperties;

	@Inject
	@Named(NomesBeans.SPRING_BATCH_BANCO_PROPERTIES)
	private BancoProperties springBatchBancoProperties;

	@Inject
	@Named(NomesBeans.PROGRAMA_BANCO_PROPERTIES)
	private BancoProperties programaBancoProperties;

	public String getDiretorioArquivoDeUsuarios() {
		return diretorioArquivoDeUsuarios;
	}

	public void setDiretorioArquivoDeUsuarios(String diretorioArquivoDeUsuarios) {
		this.diretorioArquivoDeUsuarios = diretorioArquivoDeUsuarios;
	}

	public long getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(long totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

	public double getFatorChanceLancamentoExcecao() {
		return fatorChanceLancamentoExcecao;
	}

	public void setFatorChanceLancamentoExcecao(double fatorChanceLancamentoExcecao) {
		this.fatorChanceLancamentoExcecao = fatorChanceLancamentoExcecao;
	}

	public CriptografiaProperties getCriptografiaProperties() {
		return criptografiaProperties;
	}

	public void setCriptografiaProperties(CriptografiaProperties criptografiaProperties) {
		this.criptografiaProperties = criptografiaProperties;
	}

	public AquisicaoUsuariosProperties getAquisicaoUsuariosProperties() {
		return aquisicaoUsuariosProperties;
	}

	public void setAquisicaoUsuariosProperties(
			AquisicaoUsuariosProperties aquisicaoUsuariosProperties) {
		this.aquisicaoUsuariosProperties = aquisicaoUsuariosProperties;
	}

	public PersistenciaUsuariosProperties getPersistenciaUsuariosProperties() {
		return persistenciaUsuariosProperties;
	}

	public void setPersistenciaUsuariosProperties(
			PersistenciaUsuariosProperties persistenciaUsuariosProperties) {
		this.persistenciaUsuariosProperties = persistenciaUsuariosProperties;
	}
}
