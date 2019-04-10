package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.CriadorUsuariosProperties;

@Component
public final class ArquivoUsuariosUtil {

	private static final String PREFIXO_NOME_ARQUIVO = "usuarios-";

	private static final String SUFIXO_ARQUIVO_CSV = ".csv";

	private static final String SUFIXO_ARQUIVO_TEMPORARIO = ".tmp";

	private static final String TEMPLATE_NOME_ARQUIVO = PREFIXO_NOME_ARQUIVO + "%s"
			+ SUFIXO_ARQUIVO_CSV;

	private static final String TEMPLATE_NOME_ARQUIVO_TEMPORARIO = "%s.%d"
			+ SUFIXO_ARQUIVO_TEMPORARIO;

	@Inject
	private CriadorUsuariosProperties meuJobProperties;

	@Inject
	private ArquivoUtil arquivoUtil;

	public String elaborarNomeArquivo(LocalDateTime localDateTime) {
		return String.format(TEMPLATE_NOME_ARQUIVO,
				LocalDateTimeUtil.formatarParaNomeDeArquivo(localDateTime));
	}

	public String elaborarCaminhoArquivo(LocalDateTime localDateTime) {
		String diretorio = obterCaminhoDiretorioUsuarios();
		String arquivo = elaborarNomeArquivo(localDateTime);
		return diretorio + arquivo;
	}

	private String obterCaminhoDiretorioUsuarios() {
		return arquivoUtil
				.formatarCaminhoDiretorio(meuJobProperties.getDiretorioArquivoDeUsuarios());
	}

	public String elaborarCaminhoArquivoTemporario(String caminhoArquivo, int indice) {
		return String.format(TEMPLATE_NOME_ARQUIVO_TEMPORARIO, caminhoArquivo, indice);
	}

	public List<String> obterCaminhoTodosArquivosDaData(LocalDateTime localDateTime) {

		String inicioNomeArquivos = PREFIXO_NOME_ARQUIVO
				+ LocalDateTimeUtil.formatarParaNomeDeArquivo(localDateTime);

		File diretorioUsuarios = new File(obterCaminhoDiretorioUsuarios());

		File[] vetorCaminhoArquivos = diretorioUsuarios
				.listFiles((diretorio, nome) -> nome.startsWith(inicioNomeArquivos));

		List<String> caminhoArquivos = Arrays.asList(vetorCaminhoArquivos)
				.stream()
				.map(File::getAbsolutePath)
				.collect(Collectors.toList());

		return new ArrayList<>(caminhoArquivos);
	}

	public List<String> obterCaminhoArquivosTemporariosDaData(LocalDateTime localDateTime) {

		String inicioNomeArquivos = PREFIXO_NOME_ARQUIVO
				+ LocalDateTimeUtil.formatarParaNomeDeArquivo(localDateTime);

		File diretorioUsuarios = new File(obterCaminhoDiretorioUsuarios());

		File[] vetorCaminhoArquivos = diretorioUsuarios
				.listFiles((diretorio, nome) -> nome.startsWith(inicioNomeArquivos)
						&& nome.endsWith(SUFIXO_ARQUIVO_TEMPORARIO));

		List<String> caminhoArquivos = Arrays.asList(vetorCaminhoArquivos)
				.stream()
				.map(File::getAbsolutePath)
				.collect(Collectors.toList());

		return new ArrayList<>(caminhoArquivos);
	}
}
