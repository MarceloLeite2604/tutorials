package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.csv.writer.UsuarioCsvWriter;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.contexto.PersistirClientesContexto;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.stereotype.Component;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;

@Component
@StepScope
public class PersistirClientesItemStreamReader implements ItemStreamReader<UsuarioCsvVO> {

	@Inject
	private PersistirClientesContexto contextoExecucao;

	private Iterator<UsuarioCsvVO> iteratorUsuarioCsvVO;

	@Override
	public void open(ExecutionContext executionContext) {
		contextoExecucao.restaurarContexto(executionContext);
		criarIteratorUsuarioCsvVo();
	}

	private void criarIteratorUsuarioCsvVo() {
		iteratorUsuarioCsvVO = new CsvToBeanBuilder<UsuarioCsvVO>(
				criarBufferedReaderArquivoTemporario()).withType(UsuarioCsvVO.class)
						.withSeparator(UsuarioCsvWriter.CARACTERE_SEPARADOR_DADOS)
						.withIgnoreQuotations(true)
						.withMappingStrategy(criarColumnPositionMappingStrategy())
						.withSkipLines((int) contextoExecucao.getRegistrosEscritos() + 1)
						.build()
						.iterator();
	}

	private MappingStrategy<UsuarioCsvVO> criarColumnPositionMappingStrategy() {
		MappingStrategy<UsuarioCsvVO> mappingStrategy = new ColumnPositionMappingStrategy<>();
		mappingStrategy.setType(UsuarioCsvVO.class);
		return mappingStrategy;
	}

	private BufferedReader criarBufferedReaderArquivoTemporario() {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = Files
					.newBufferedReader(Paths.get(contextoExecucao.getCaminhoArquivoTemporario()));
		} catch (IOException excecao) {
			throw new RuntimeException("Erro ao abrir o arquivo temporário \""
					+ contextoExecucao.getCaminhoArquivoTemporario() + "\".", excecao);
		}
		return bufferedReader;
	}

	@Override
	public void update(ExecutionContext executionContext) {
		contextoExecucao.salvarContexto(executionContext);
	}

	@Override
	public void close() {
	}

	@Override
	public UsuarioCsvVO read() throws Exception {

		UsuarioCsvVO usuarioCsvVo = null;

		if (iteratorUsuarioCsvVO.hasNext()) {
			usuarioCsvVo = iteratorUsuarioCsvVO.next();
			contextoExecucao.setRegistrosEscritos(contextoExecucao.getRegistrosEscritos() + 1);
		}

		return usuarioCsvVo;
	}

}
