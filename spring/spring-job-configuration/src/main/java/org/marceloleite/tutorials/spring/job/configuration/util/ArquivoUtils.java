package org.marceloleite.tutorials.spring.job.configuration.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class ArquivoUtils {

	private ContadorLinhasArquivo contadorLinhasArquivo = new ContadorLinhasArquivo();

	public InputStream criarInputStream(Path path, OpenOption... openOptions) throws IOException {
		return Files.newInputStream(path, openOptions);
	}

	public OutputStream criarOutputStream(Path path, OpenOption... openOptions) throws IOException {
		return Files.newOutputStream(path, openOptions);
	}

	public BufferedWriter criarBufferedWriter(Path path, Charset charset, OpenOption... openOptions)
			throws IOException {
		return Files.newBufferedWriter(path, charset, openOptions);
	}

	public BufferedReader criarBufferedReader(Path path, Charset charset) throws IOException {
		return Files.newBufferedReader(path, charset);
	}

	public Stream<String> obterStreamLinhas(Path path) throws IOException {
		return Files.lines(path);
	}

	public Path obterPath(String value) {
		return Paths.get(value);
	}

	public void criarArquivoSobrescrevendoExistente(String caminho, String conteudo) {
		excluirArquivo(caminho);
		escreverConteudoArquivo(caminho, conteudo);
	}

	private void escreverConteudoArquivo(String caminho, String conteudo) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(caminho);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,
						StandardCharsets.UTF_8);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
			bufferedWriter.write(conteudo);
		} catch (IOException excecao) {
			throw new RuntimeException(
					"Erro ao escrever o conteúdo no arquivo \"" + caminho + "\".", excecao);
		}
	}

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

	public boolean arquivoExiste(String caminho) {
		return new File(caminho).exists();
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

	public void copiarArquivo(String caminhoArquivoOrigem, String caminhoArquivoDestino)
			throws IOException {
		copiarArquivo(Paths.get(caminhoArquivoOrigem), Paths.get(caminhoArquivoDestino));
	}

	public void copiarArquivo(Path pathArquivoOrigem, Path pathArquivoDestino) throws IOException {
		Files.write(pathArquivoDestino, Files.readAllBytes(pathArquivoOrigem));
	}
}