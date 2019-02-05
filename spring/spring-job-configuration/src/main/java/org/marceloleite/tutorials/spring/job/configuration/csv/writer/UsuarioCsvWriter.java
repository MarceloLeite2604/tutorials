package org.marceloleite.tutorials.spring.job.configuration.csv.writer;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.marceloleite.tutorials.spring.job.configuration.VerificadorLancamentoExcecaoSimulada;
import org.marceloleite.tutorials.spring.job.configuration.comparator.OrdemColunasUsuarioCsvVoComparator;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Component
public class UsuarioCsvWriter {

	private static final char CARACTERE_SEPARADOR_DADOS = ';';

	private static final OpenOption[] OPEN_OPTIONS_APPEND = { StandardOpenOption.APPEND };

	@Value("${programa.caminhoArquivoSaida}")
	private String caminhoArquivoSaida;

	private VerificadorLancamentoExcecaoSimulada verificadorLancamentoExcecaoSimulada;

	public UsuarioCsvWriter() {
		verificadorLancamentoExcecaoSimulada = new VerificadorLancamentoExcecaoSimulada(0.2);
	}

	public void escrever(List<UsuarioCsvVO> usuarioCsvVOs) {

		System.out.println("Registros: " + usuarioCsvVOs.size());
		verificadorLancamentoExcecaoSimulada.verificar();

		MappingStrategy<UsuarioCsvVO> mappingStrategy = elaborarMappingStrategy();

		try (Writer writer = abrirArquivo()) {

			StatefulBeanToCsv<UsuarioCsvVO> statefulBeanToCsv = new StatefulBeanToCsvBuilder<UsuarioCsvVO>(
					writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
							.withSeparator(CARACTERE_SEPARADOR_DADOS)
							.withMappingStrategy(mappingStrategy)
							.build();

			statefulBeanToCsv.write(usuarioCsvVOs);
		} catch (IOException excecao) {
			throw new RuntimeException("Erro ao abrir o arquivo \"" + caminhoArquivoSaida
					+ "\" para escrita dos usuarios.", excecao);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException excecao) {
			throw new RuntimeException("Erro ao escrever os dados do usuário "
					+ excecao.getLineNumber() + " no arquivo \"" + caminhoArquivoSaida + "\".",
					excecao);
		}
	}

	private MappingStrategy<UsuarioCsvVO> elaborarMappingStrategy() {
		MappingStrategy<UsuarioCsvVO> mappingStrategy = null;
		if (!arquivoDeSaidaExiste()) {
			mappingStrategy = new HeaderColumnNameMappingStrategy<>();
			mappingStrategy.setType(UsuarioCsvVO.class);
			((HeaderColumnNameMappingStrategy<UsuarioCsvVO>) mappingStrategy)
					.setColumnOrderOnWrite(new OrdemColunasUsuarioCsvVoComparator());
		}
		return mappingStrategy;
	}

	public Writer abrirArquivo() throws IOException {
		OpenOption[] openOptions = new OpenOption[0];

		if (arquivoDeSaidaExiste()) {
			openOptions = OPEN_OPTIONS_APPEND;
		}

		return Files.newBufferedWriter(Paths.get(caminhoArquivoSaida), openOptions);
	}

	private boolean arquivoDeSaidaExiste() {
		return Files.exists(Paths.get(caminhoArquivoSaida));
	}
}
