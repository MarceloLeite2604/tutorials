package org.marceloleite.tutorials.spring.job.configuration.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

@Component
public class ContadorLinhasArquivo {

	public long contabilizarLinhas(String caminhoArquivo) {
		return abrirArquivo(caminhoArquivo);
	}

	private long abrirArquivo(String caminhoArquivo) {
		File arquivoScore = new File(caminhoArquivo);

		if (arquivoScore.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(arquivoScore);
					InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
							StandardCharsets.UTF_8);
					LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader)) {

				return contabilizarLinhasArquivo(lineNumberReader);

			} catch (IOException excecao) {
				throw new RuntimeException("Erro ao analisar o número de linhas do arquivo \""
						+ caminhoArquivo + "\".", excecao);
			}

		} else {
			throw new RuntimeException("O arquivo \"" + caminhoArquivo + "\" não existe.");
		}
	}

	private long contabilizarLinhasArquivo(LineNumberReader lineNumberReader) throws IOException {
		String conteudoRegistro;
		long totalLinhas = 0L;
		do {
			conteudoRegistro = lineNumberReader.readLine();
			if (conteudoRegistro != null && !conteudoRegistro.isEmpty()) {
				totalLinhas++;
			}
		} while (conteudoRegistro != null);
		return totalLinhas;
	}
}
