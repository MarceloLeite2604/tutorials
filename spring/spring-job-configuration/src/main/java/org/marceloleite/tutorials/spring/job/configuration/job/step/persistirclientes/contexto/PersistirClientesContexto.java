package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.contexto;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.job.step.geral.AbstractContexto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class PersistirClientesContexto extends AbstractContexto {

	private String caminhoArquivoTemporario;

	private long registrosEscritos;

	private long totalDeRegistros;

	@Inject
	@Named("persistirClientesProperties")
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
		return adquirir(PersistirClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				executionContext);
	}

	private Long obterTotalDeRegistros(ExecutionContext executionContext) {
		String valor = adquirir(PersistirClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				executionContext);
		return Long.parseLong(valor);
	}

	private Long obterRegistrosEscritos(ExecutionContext executionContext) {
		String valor = adquirir(PersistirClientesContextoPropriedade.REGISTROS_ESCRITOS,
				executionContext);
		return Long.parseLong(valor);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosEscritos(executionContext);
		definirTotalRegistros(executionContext);
		definirCaminhoArquivoTemporario(executionContext);
	}

	private void definirRegistrosEscritos(ExecutionContext executionContext) {
		definir(PersistirClientesContextoPropriedade.REGISTROS_ESCRITOS,
				Long.toString(registrosEscritos), executionContext);
	}

	private void definirTotalRegistros(ExecutionContext executionContext) {
		definir(PersistirClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				Long.toString(totalDeRegistros), executionContext);
	}

	private void definirCaminhoArquivoTemporario(ExecutionContext executionContext) {
		definir(PersistirClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				caminhoArquivoTemporario, executionContext);
	}

	@Override
	protected Properties obterProperties() {
		return persistirClientesProperties;
	}

}
