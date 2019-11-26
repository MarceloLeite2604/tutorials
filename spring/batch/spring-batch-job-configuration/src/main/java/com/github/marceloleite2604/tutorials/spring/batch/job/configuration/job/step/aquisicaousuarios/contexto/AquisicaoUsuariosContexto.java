package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.contexto;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo.PropriedadeBO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.enumeration.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.CriadorUsuariosProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.LocalDateTimeUtil;
import java.time.LocalDateTime;
import java.util.Map;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class AquisicaoUsuariosContexto {

	private long totalDeRegistros;

	private long registrosLidos;

	private long registrosEscritos;

	private String caminhoArquivoDeUsuarios;

	@Inject
	private PropriedadeBO propriedadeBO;

	@Inject
	private CriadorUsuariosProperties criadorUsuariosPropriedades;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuariosUtil;
	
	@Inject
	private LocalDateTimeUtil localDateTimeUtil;

	@Value("#{jobParameters}")
	private Map<String, Object> jobParameters;

	private String valorPadraoCaminhoArquivoUsuarios;

	public long getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(long totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

	public long getRegistrosLidos() {
		return registrosLidos;
	}

	public void setRegistrosLidos(long registrosLidos) {
		this.registrosLidos = registrosLidos;
	}

	public long getRegistrosEscritos() {
		return registrosEscritos;
	}

	public void setRegistrosEscritos(long registrosEscritos) {
		this.registrosEscritos = registrosEscritos;
	}

	public void restaurarContexto(ExecutionContext executionContext) {
		registrosEscritos = obterRegistrosEscritos(executionContext);
		registrosLidos = obterRegistrosLidos(executionContext);
		totalDeRegistros = obterTotalRegistros(executionContext);
		caminhoArquivoDeUsuarios = obterCaminhoArquivoDeUsuarios(executionContext);
	}

	private String obterCaminhoArquivoDeUsuarios(ExecutionContext executionContext) {
		return propriedadeBO.obter(AquisicaoUsuariosContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				executionContext, obterValorPadraoCaminhoArquivoUsuarios());
	}

	private Long obterRegistrosEscritos(ExecutionContext executionContext) {
		String stringValor = propriedadeBO.obter(
				AquisicaoUsuariosContextoPropriedade.REGISTROS_ESCRITOS, executionContext, NumberUtils.LONG_ZERO.toString());
		return Long.parseLong(stringValor);
	}

	private Long obterRegistrosLidos(ExecutionContext executionContext) {
		String stringValor = propriedadeBO.obter(
				AquisicaoUsuariosContextoPropriedade.REGISTROS_LIDOS, executionContext, NumberUtils.LONG_ZERO.toString());
		return Long.parseLong(stringValor);
	}

	private Long obterTotalRegistros(ExecutionContext executionContext) {

		String valorPadraoTotalRegistros = Long
				.toString(criadorUsuariosPropriedades.getTotalDeRegistros());

		String stringValor = propriedadeBO.obter(
				AquisicaoUsuariosContextoPropriedade.TOTAL_DE_REGISTROS, executionContext,
				valorPadraoTotalRegistros);

		return Long.parseLong(stringValor);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosLidos(executionContext);
		definirRegistrosEscritos(executionContext);
		definirTotalRegistros(executionContext);
		definirCaminhoArquivoDeUsuarios(executionContext);
	}

	private void definirRegistrosLidos(ExecutionContext executionContext) {
		propriedadeBO.definir(AquisicaoUsuariosContextoPropriedade.REGISTROS_LIDOS,
				Long.toString(registrosLidos), executionContext);
	}

	private void definirRegistrosEscritos(ExecutionContext executionContext) {
		propriedadeBO.definir(AquisicaoUsuariosContextoPropriedade.REGISTROS_ESCRITOS,
				Long.toString(registrosEscritos), executionContext);
	}

	private void definirTotalRegistros(ExecutionContext executionContext) {
		propriedadeBO.definir(AquisicaoUsuariosContextoPropriedade.TOTAL_DE_REGISTROS,
				Long.toString(totalDeRegistros), executionContext);
	}

	private void definirCaminhoArquivoDeUsuarios(ExecutionContext executionContext) {
		propriedadeBO.definir(AquisicaoUsuariosContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				caminhoArquivoDeUsuarios, executionContext);
	}

	private String obterValorPadraoCaminhoArquivoUsuarios() {
		if (valorPadraoCaminhoArquivoUsuarios == null) {
			String instante = (String) jobParameters
					.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

			LocalDateTime localDateTime = localDateTimeUtil.parseDataParaNomeArquivo(instante);

			valorPadraoCaminhoArquivoUsuarios = arquivoUsuariosUtil
					.elaborarCaminhoArquivo(localDateTime);
		}
		return valorPadraoCaminhoArquivoUsuarios;
	}
}
