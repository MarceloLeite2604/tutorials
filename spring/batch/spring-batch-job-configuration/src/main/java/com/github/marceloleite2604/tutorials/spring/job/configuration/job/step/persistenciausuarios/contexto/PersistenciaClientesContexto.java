package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.persistenciausuarios.contexto;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.geral.AbstractContexto;

@Component
@StepScope
public class PersistenciaClientesContexto extends AbstractContexto {

	private String caminhoArquivoTemporario;

	private long registrosEscritos;

	private long totalDeRegistros;

	@Inject
	@Named(NomesBeans.PERSISTENCIA_USUARIOS_PROPERTIES)
	private Properties persistirClientesProperties;

	public String getCaminhoArquivoTemporario() {
		return caminhoArquivoTemporario;
	}

	public void setCaminhoArquivoTemporario(String caminhoArquivoTemporario) {
		this.caminhoArquivoTemporario = caminhoArquivoTemporario;
	}

	public long getRegistrosEscritos() {
		return registrosEscritos;
	}

	public void setRegistrosEscritos(long registrosEscritos) {
		this.registrosEscritos = registrosEscritos;
	}

	public long getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(long totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

	public void restaurarContexto(ExecutionContext executionContext) {
		setCaminhoArquivoTemporario(obterCaminhoArquivoTemporario(executionContext));
		setTotalDeRegistros(obterTotalDeRegistros(executionContext));
		setRegistrosEscritos(obterRegistrosEscritos(executionContext));
	}

	private String obterCaminhoArquivoTemporario(ExecutionContext executionContext) {
		return adquirir(PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				executionContext);
	}

	private Long obterTotalDeRegistros(ExecutionContext executionContext) {
		String valor = adquirir(PersistenciaClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				executionContext);
		return Long.parseLong(valor);
	}

	private Long obterRegistrosEscritos(ExecutionContext executionContext) {
		String valor = adquirir(PersistenciaClientesContextoPropriedade.REGISTROS_ESCRITOS,
				executionContext);
		return Long.parseLong(valor);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosEscritos(executionContext);
		definirTotalDeRegistros(executionContext);
		definirCaminhoArquivoTemporario(executionContext);
	}

	private void definirRegistrosEscritos(ExecutionContext executionContext) {
		definir(PersistenciaClientesContextoPropriedade.REGISTROS_ESCRITOS,
				Long.toString(registrosEscritos), executionContext);
	}

	private void definirTotalDeRegistros(ExecutionContext executionContext) {
		definir(PersistenciaClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				Long.toString(totalDeRegistros), executionContext);
	}

	private void definirCaminhoArquivoTemporario(ExecutionContext executionContext) {
		definir(PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				caminhoArquivoTemporario, executionContext);
	}

	@Override
	protected Properties obterProperties() {
		return persistirClientesProperties;
	}

}
