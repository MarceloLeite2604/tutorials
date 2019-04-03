package org.marceloleite.tutorials.spring.job.configuration.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class ArquivoUtil {

	@Inject
	private ContadorLinhasArquivo contadorLinhasArquivo;

	public void excluirArquivo(String caminho) {
		File arquivo = new File(caminho);
		if (arquivo.exists()) {
			try {
				FileUtils.forceDelete(arquivo);
			} catch (IOException excecao) {
				throw new RuntimeException("Erro ao tentar excluir o arquivo \"" + caminho + "\".",
						excecao);
			}
		}
	}

	public void excluirArquivos(List<String> caminhos) {
		for (String caminho : caminhos) {
			excluirArquivo(caminho);
		}
	}

	public String obterNomeArquivo(String caminho) {
		return new File(caminho).getName()
				.replaceAll("\\.[^\\.]*$", "");
	}

	public File obterDiretorio(String caminho) {
		return Optional.ofNullable(new File(caminho).getParentFile())
				.orElse(new File("."));
	}

	public long obterTotalLinhasArquivo(String caminhoArquivo) {
		return contadorLinhasArquivo.contabilizarLinhas(caminhoArquivo);
	}

	public String obterNomeArquivoSemExtensao(String caminhoArquivo) {
		return new File(caminhoArquivo).getName()
				.replaceAll("\\.[^\\.]*$", "");
	}

	public String formatarCaminhoDiretorio(String caminhoDiretorio) {
		String diretorio = caminhoDiretorio;

		if (!diretorio.endsWith(File.separator)) {
			diretorio += File.separator;
		}

		return diretorio;
	}
	
	public boolean existe(String caminhoArquivo) {
		return Paths.get(caminhoArquivo).toFile().exists();
	}
}