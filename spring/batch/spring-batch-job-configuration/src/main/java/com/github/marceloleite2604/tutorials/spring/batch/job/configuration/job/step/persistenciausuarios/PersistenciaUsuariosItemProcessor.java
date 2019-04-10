package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.mapper.UsuarioCsvVoParaUsuarioPoMapper;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.po.UsuarioPO;

@Component
public class PersistenciaUsuariosItemProcessor implements ItemProcessor<UsuarioCsvVO, UsuarioPO> {

	@Inject
	private UsuarioCsvVoParaUsuarioPoMapper usuarioCsvVoParaUsuarioPoMapper;

	@Override
	public UsuarioPO process(UsuarioCsvVO usuarioCsvVO) throws Exception {
		return usuarioCsvVoParaUsuarioPoMapper.map(usuarioCsvVO);
	}

}
