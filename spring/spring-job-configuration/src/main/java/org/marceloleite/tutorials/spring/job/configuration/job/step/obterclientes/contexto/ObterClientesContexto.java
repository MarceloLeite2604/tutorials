package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.contexto;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.job.step.geral.AbstractContexto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ObterClientesContexto extends AbstractContexto {

	private long totalDeRegistros;

	private long registrosEscritos;

	private String caminhoArquivoDeUsuarios;

	@Inject
	@Named("obterClientesProperties")
	private Properties obterClientesProperties;

	public long getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(long totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

	public long getRegistrosEscritos() {
		return registrosEscritos;
	}

	public void setRegistrosEscritos(long registrosEscritos) {
		this.registrosEscritos = registrosEscritos;
	}

	public void restaurarContexto(ExecutionContext executionContext) {
		registrosEscritos = obterRegistrosEscritos(executionContext);
		totalDeRegistros = obterTotalRegistros(executionContext);
		caminhoArquivoDeUsuarios = obterCaminhoArquivoDeUsuarios(executionContext);
	}

	private String obterCaminhoArquivoDeUsuarios(ExecutionContext executionContext) {
		return adquirir(ObterClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				executionContext);
	}

	private Long obterRegistrosEscritos(ExecutionContext executionContext) {
		String valor = adquirir(ObterClientesContextoPropriedade.REGISTROS_ESCRITOS,
				executionContext);
		return Long.parseLong(valor);
	}

	private Long obterTotalRegistros(ExecutionContext executionContext) {
		String valor = adquirir(ObterClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				executionContext);
		return Long.parseLong(valor);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosEscritos(executionContext);
		definirTotalRegistros(executionContext);
		definirCaminhoArquivoDeUsuarios(executionContext);
	}

	private void definirRegistrosEscritos(ExecutionContext executionContext) {
		definir(ObterClientesContextoPropriedade.REGISTROS_ESCRITOS,
				Long.toString(registrosEscritos), executionContext);
	}

	private void definirTotalRegistros(ExecutionContext executionContext) {
		definir(ObterClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				Long.toString(totalDeRegistros), executionContext);
	}

	private void definirCaminhoArquivoDeUsuarios(ExecutionContext executionContext) {
		definir(ObterClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				caminhoArquivoDeUsuarios, executionContext);
	}

	@Override
	protected Properties obterProperties() {
		return obterClientesProperties;
	}
}
