package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.csv.writer.UsuarioCsvWriter;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component
public class ObterClientesItemWriter implements ItemStreamWriter<UsuarioCsvVO> {

	@Inject
	private UsuarioCsvWriter usuarioCsvWriter;

	@Override
	public void write(List<? extends UsuarioCsvVO> usuarioCsvVOs) throws Exception {
		usuarioCsvWriter.escrever((List<UsuarioCsvVO>) usuarioCsvVOs);
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws ItemStreamException {
		// TODO Auto-generated method stub

	}

}
