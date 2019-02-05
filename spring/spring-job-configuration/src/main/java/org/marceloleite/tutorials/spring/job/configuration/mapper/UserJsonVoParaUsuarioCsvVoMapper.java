package org.marceloleite.tutorials.spring.job.configuration.mapper;

import java.util.Optional;

import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.LoginJsonVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.NameJsonVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.stereotype.Component;

@Component
public class UserJsonVoParaUsuarioCsvVoMapper implements Mapper<UserJsonVO, UsuarioCsvVO> {

	@Override
	public UsuarioCsvVO map(UserJsonVO userJsonVO) {

		NameJsonVO nameJsonVO = userJsonVO.getNameJsonVO();
		LoginJsonVO loginJsonVO = userJsonVO.getLoginJsonVO();

		String uuid = Optional.ofNullable(loginJsonVO)
				.map(LoginJsonVO::getUuid)
				.orElse("");

		String usuario = Optional.ofNullable(loginJsonVO)
				.map(LoginJsonVO::getUsername)
				.orElse("");

		String nome = Optional.ofNullable(nameJsonVO)
				.map(NameJsonVO::getFirst)
				.orElse("");

		String sobrenome = Optional.ofNullable(nameJsonVO)
				.map(NameJsonVO::getLast)
				.orElse("");

		return UsuarioCsvVO.builder()
				.uuid(uuid)
				.nome(nome)
				.sobrenome(sobrenome)
				.usuario(usuario)
				.build();
	}

}
