package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.mapper.UsuarioCsvVoParaUsuarioPoMapper;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersistirClientesItemProcessor implements ItemProcessor<UsuarioCsvVO, UsuarioPO> {

	@Inject
	private UsuarioCsvVoParaUsuarioPoMapper usuarioCsvVoParaUsuarioPoMapper;

	@Override
	public UsuarioPO process(UsuarioCsvVO usuarioCsvVO) throws Exception {
		return usuarioCsvVoParaUsuarioPoMapper.map(usuarioCsvVO);
	}

}
