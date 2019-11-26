package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.contexto;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo.PropriedadeBO;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class PersistenciaClientesContexto {

	private String caminhoArquivoTemporario;

	private long registrosEscritos;

	private long totalDeRegistros;

	@Inject
	private PropriedadeBO propriedadeBO;

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
		return propriedadeBO.obterObrigatorio(
				PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				executionContext);
	}

	private Long obterTotalDeRegistros(ExecutionContext executionContext) {
		String valor = propriedadeBO.obterObrigatorio(
				PersistenciaClientesContextoPropriedade.TOTAL_DE_REGISTROS, executionContext);
		return Long.parseLong(valor);
	}

	private Long obterRegistrosEscritos(ExecutionContext executionContext) {
		String valor = propriedadeBO.obter(
				PersistenciaClientesContextoPropriedade.REGISTROS_ESCRITOS, executionContext,
				NumberUtils.LONG_ZERO.toString());
		return Long.parseLong(valor);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosEscritos(executionContext);
		definirTotalDeRegistros(executionContext);
		definirCaminhoArquivoTemporario(executionContext);
	}

	private void definirRegistrosEscritos(ExecutionContext executionContext) {
		propriedadeBO.definir(PersistenciaClientesContextoPropriedade.REGISTROS_ESCRITOS,
				Long.toString(registrosEscritos), executionContext);
	}

	private void definirTotalDeRegistros(ExecutionContext executionContext) {
		propriedadeBO.definir(PersistenciaClientesContextoPropriedade.TOTAL_DE_REGISTROS,
				Long.toString(totalDeRegistros), executionContext);
	}

	private void definirCaminhoArquivoTemporario(ExecutionContext executionContext) {
		propriedadeBO.definir(PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				caminhoArquivoTemporario, executionContext);
	}
}
