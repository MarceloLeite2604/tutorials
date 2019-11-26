package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util;

import com.github.marceloleite2604.util.file.FileUtil;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class ArquivoUtil {

	@Inject
	private ContadorLinhasArquivo contadorLinhasArquivo;

	@Inject
	private FileUtil fileUtil;

	public void excluirArquivo(String caminho) {
		fileUtil.delete(caminho);
	}

	public void excluirArquivos(List<String> caminhos) {
		caminhos.stream()
				.forEach(this::excluirArquivo);
	}

	public String obterNomeArquivoSemExtensao(String caminho) {
		return fileUtil.retrieveFileNameWithoutExtension(caminho);
	}

	public File obterDiretorio(String caminho) {
		return fileUtil.retrieveDirectory(caminho);
	}

	public long obterTotalLinhasArquivo(String caminhoArquivo) {
		return contadorLinhasArquivo.contabilizarLinhas(caminhoArquivo);
	}

	public String formatarCaminhoDiretorio(String caminhoDiretorio) {
		return fileUtil.appendSeparatorIfNecessary(caminhoDiretorio);
	}

	public boolean existe(String caminhoArquivo) {
		return fileUtil.fileExists(caminhoArquivo);
	}
}