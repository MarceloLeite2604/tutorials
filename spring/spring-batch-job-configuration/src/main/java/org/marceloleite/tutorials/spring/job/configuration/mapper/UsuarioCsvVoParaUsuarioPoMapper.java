package org.marceloleite.tutorials.spring.job.configuration.mapper;

import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.stereotype.Component;

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
