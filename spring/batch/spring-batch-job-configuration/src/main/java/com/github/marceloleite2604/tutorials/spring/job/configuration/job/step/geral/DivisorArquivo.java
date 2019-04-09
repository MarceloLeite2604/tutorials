package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.geral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.ArquivoUtil;

@Component
public class DivisorArquivo {

	@Inject
	private ArquivoUtil arquivoUtils;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuarios;

	private String cabecalhoCsv;

	public Map<String, Long> dividirArquivo(String caminhoArquivo, int numeroDivisoes) {

		try (FileInputStream fileIputStream = new FileInputStream(caminhoArquivo);
				InputStreamReader inputStreamReader = new InputStreamReader(fileIputStream,
						StandardCharsets.UTF_8);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			return criarArquivosTemporarios(caminhoArquivo, numeroDivisoes, bufferedReader);

		} catch (IOException excecao) {
			throw new SpringBatchJobConfigurationRuntimeException(
					"Erro ao realizar a divisão do arquivo \"" + caminhoArquivo + "\".", excecao);
		}
	}

	private Map<String, Long> criarArquivosTemporarios(String caminhoArquivo, int numeroDivisoes,
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

		Map<String, Long> registrosPorArquivoTemporario = new HashMap<>();

		long linhasPorArquivo = totalRegistrosArquivo / totalArquivosTemporarios;
		long linhasRestantes = totalRegistrosArquivo % totalArquivosTemporarios;

		for (int contadorArquivos = 0; contadorArquivos < totalArquivosTemporarios; contadorArquivos++) {
			Entry<String, Long> entryArquivoTemporario = criarArquivoTemporario(caminhoArquivo,
					bufferedReaderArquivo, contadorArquivos, totalArquivosTemporarios,
					linhasPorArquivo, linhasRestantes);

			registrosPorArquivoTemporario.put(entryArquivoTemporario.getKey(),
					entryArquivoTemporario.getValue());
		}

		return registrosPorArquivoTemporario;
	}

	private Entry<String, Long> criarArquivoTemporario(String caminhoArquivoOriginal,
			BufferedReader bufferedReaderArquivoOriginal, int indiceArquivo, int totalArquivos,
			long linhasPorArquivo, long linhasRestantes) throws IOException {

		String caminhoArquivoTemporario = arquivoUsuarios
				.elaborarCaminhoArquivoTemporario(caminhoArquivoOriginal, indiceArquivo);

		arquivoUtils.excluirArquivo(caminhoArquivoTemporario);

		try (BufferedWriter bufferedWriterArquivoTemporario = Files
				.newBufferedWriter(Paths.get(caminhoArquivoTemporario), StandardCharsets.UTF_8)) {

			escreverCabecalhoCsv(indiceArquivo, bufferedReaderArquivoOriginal,
					bufferedWriterArquivoTemporario);

			long totalLinhasArquivoTemporario = 0;
			while (totalLinhasArquivoTemporario < linhasPorArquivo) {
				bufferedWriterArquivoTemporario
						.write(bufferedReaderArquivoOriginal.readLine() + "\n");
				totalLinhasArquivoTemporario++;
			}

			if (indiceArquivo + 1 == totalArquivos) {
				for (int contadorLinhasRestantes = 0; contadorLinhasRestantes < linhasRestantes; contadorLinhasRestantes++) {
					bufferedWriterArquivoTemporario
							.write(bufferedReaderArquivoOriginal.readLine() + "\n");
					totalLinhasArquivoTemporario++;
				}
			}
			return new AbstractMap.SimpleEntry<>(caminhoArquivoTemporario,
					totalLinhasArquivoTemporario);
		}
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
