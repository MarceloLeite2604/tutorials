package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.contexto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.geral.AbstractContexto;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.LocalDateTimeUtil;

@Component
@StepScope
public class AquisicaoUsuariosContexto extends AbstractContexto {

	private long totalDeRegistros;

	private long registrosLidos;

	private long registrosEscritos;

	private String caminhoArquivoDeUsuarios;

	@Inject
	@Named(NomesBeans.AQUISICAO_USUARIOS_PROPERTIES)
	private Properties aquisicaoUsuariosProperties;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuariosUtil;

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
		return adquirir(AquisicaoUsuariosContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				executionContext, obterValorPadraoCaminhoArquivoUsuarios());
	}

	private Long obterRegistrosEscritos(ExecutionContext executionContext) {
		String valor = adquirir(AquisicaoUsuariosContextoPropriedade.REGISTROS_ESCRITOS,
				executionContext);
		return Long.parseLong(valor);
	}

	private Long obterRegistrosLidos(ExecutionContext executionContext) {
		String valor = adquirir(AquisicaoUsuariosContextoPropriedade.REGISTROS_LIDOS,
				executionContext, obterRegistrosEscritos(executionContext).toString());
		return Long.parseLong(valor);
	}

	private Long obterTotalRegistros(ExecutionContext executionContext) {
		String valor = adquirir(AquisicaoUsuariosContextoPropriedade.TOTAL_DE_REGISTROS,
				executionContext);
		return Long.parseLong(valor);
	}

	public void salvarContexto(ExecutionContext executionContext) {
		definirRegistrosLidos(executionContext);
		definirRegistrosEscritos(executionContext);
		definirTotalRegistros(executionContext);
		definirCaminhoArquivoDeUsuarios(executionContext);
	}

	private void definirRegistrosLidos(ExecutionContext executionContext) {
		definir(AquisicaoUsuariosContextoPropriedade.REGISTROS_LIDOS, Long.toString(registrosLidos),
				executionContext);
	}

	private void definirRegistrosEscritos(ExecutionContext executionContext) {
		definir(AquisicaoUsuariosContextoPropriedade.REGISTROS_ESCRITOS,
				Long.toString(registrosEscritos), executionContext);
	}

	private void definirTotalRegistros(ExecutionContext executionContext) {
		definir(AquisicaoUsuariosContextoPropriedade.TOTAL_DE_REGISTROS,
				Long.toString(totalDeRegistros), executionContext);
	}

	private void definirCaminhoArquivoDeUsuarios(ExecutionContext executionContext) {
		definir(AquisicaoUsuariosContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				caminhoArquivoDeUsuarios, executionContext);
	}

	private String obterValorPadraoCaminhoArquivoUsuarios() {
		if (valorPadraoCaminhoArquivoUsuarios == null) {
			String instante = (String) jobParameters
					.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

			LocalDateTime localDateTime = LocalDateTimeUtil.parseDataParaNomeArquivo(instante);

			valorPadraoCaminhoArquivoUsuarios = arquivoUsuariosUtil
					.elaborarCaminhoArquivo(localDateTime);
		}
		return valorPadraoCaminhoArquivoUsuarios;
	}

	@Override
	protected Properties obterProperties() {
		return aquisicaoUsuariosProperties;
	}
}
