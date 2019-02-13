package org.marceloleite.tutorials.spring.job.configuration.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.util.ArquivoUtils;
import org.springframework.stereotype.Component;

@Component
public class DivisorArquivo {

	private static final String EXTENSAO_ARQUIVO_TEMPORARIO = ".tmp";

	@Inject
	private ArquivoUtils arquivoUtils;

	private String cabecalhoCsv;

	public List<String> dividirArquivo(String caminhoArquivo, int numeroDivisoes) {

		try (FileInputStream fileIputStream = new FileInputStream(caminhoArquivo);
				InputStreamReader inputStreamReader = new InputStreamReader(fileIputStream,
						"UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			return criarArquivosTemporarios(caminhoArquivo, numeroDivisoes, bufferedReader);

		} catch (IOException excecao) {
			throw new RuntimeException(
					"Erro ao realizar a divisão do arquivo \"" + caminhoArquivo + "\".", excecao);
		}
	}

	private List<String> criarArquivosTemporarios(String caminhoArquivo, int numeroDivisoes,
			BufferedReader bufferedReaderArquivo) throws IOException {
		Long totalRegistrosArquivo = arquivoUtils.obterTotalLinhasArquivo(caminhoArquivo);

		// Ignora a linha do cabeçalho.
		totalRegistrosArquivo--;

		int totalArquivosTemporarios;
		if (totalRegistrosArquivo < numeroDivisoes) {
			totalArquivosTemporarios = totalRegistrosArquivo.intValue();
		} else {
			totalArquivosTemporarios = numeroDivisoes;
		}

		List<String> caminhosArquivos = new ArrayList<>(totalArquivosTemporarios);

		long linhasPorArquivo = totalRegistrosArquivo / totalArquivosTemporarios;
		long linhasRestantes = totalRegistrosArquivo % totalArquivosTemporarios;

		for (int contadorArquivos = 0; contadorArquivos < totalArquivosTemporarios; contadorArquivos++) {
			String caminhoArquivoTemporario = criarArquivoTemporario(caminhoArquivo,
					bufferedReaderArquivo, contadorArquivos, totalArquivosTemporarios,
					linhasPorArquivo, linhasRestantes);

			caminhosArquivos.add(caminhoArquivoTemporario);
		}

		return caminhosArquivos;
	}

	private String criarArquivoTemporario(String caminhoArquivoOriginal,
			BufferedReader bufferedReaderArquivoOriginal, int indiceArquivo, int totalArquivos,
			long linhasPorArquivo, long linhasRestantes) throws IOException {

		String caminhoArquivoTemporario = criarCaminhoArquivoTemporario(caminhoArquivoOriginal,
				indiceArquivo);

		arquivoUtils.excluirArquivo(caminhoArquivoTemporario);

		try (BufferedWriter bufferedWriterArquivoTemporario = arquivoUtils
				.criarBufferedWriter(Paths.get(caminhoArquivoTemporario), StandardCharsets.UTF_8)) {

			escreverCabecalhoCsv(indiceArquivo, bufferedReaderArquivoOriginal,
					bufferedWriterArquivoTemporario);

			for (int contadorLinhas = 0; contadorLinhas < linhasPorArquivo; contadorLinhas++) {
				bufferedWriterArquivoTemporario
						.write(bufferedReaderArquivoOriginal.readLine() + "\n");
			}

			if (indiceArquivo + 1 == totalArquivos) {
				for (int contadorLinhas = 0; contadorLinhas < linhasRestantes; contadorLinhas++) {
					bufferedWriterArquivoTemporario
							.write(bufferedReaderArquivoOriginal.readLine() + "\n");
				}
			}
		}

		return caminhoArquivoTemporario;

	}

	private String criarCaminhoArquivoTemporario(String caminhoArquivo, int indiceArquivo) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(arquivoUtils.obterDiretorio(caminhoArquivo));
		stringBuilder.append(File.separator);
		stringBuilder.append(arquivoUtils.obterNomeArquivoSemExtensao(caminhoArquivo));
		stringBuilder.append(indiceArquivo);
		stringBuilder.append(EXTENSAO_ARQUIVO_TEMPORARIO);

		return stringBuilder.toString();

	}

	private void escreverCabecalhoCsv(int indiceArquivo,
			BufferedReader bufferedReaderArquivoOriginal,
			BufferedWriter bufferedWriterArquivoTemporario) throws IOException {

		if (indiceArquivo == 0) {
			cabecalhoCsv = bufferedReaderArquivoOriginal.readLine() + "\n";
		}

		bufferedWriterArquivoTemporario.write(cabecalhoCsv);
	}

}
