package com.github.marceloleite2604.tutorials.spring.job.configuration.mapper;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.job.configuration.model.po.UsuarioPO;

@Component
public class UsuarioCsvVoParaUsuarioPoMapper implements Mapper<UsuarioCsvVO, UsuarioPO> {

	@Override
	public UsuarioPO map(UsuarioCsvVO usuarioCsvVO) {
		return UsuarioPO.builder()
				.uuid(usuarioCsvVO.getUuid())
				.nome(usuarioCsvVO.getNome())
				.sobrenome(usuarioCsvVO.getSobrenome())
				.usuario(usuarioCsvVO.getUsuario())
				.build();
	}

}
