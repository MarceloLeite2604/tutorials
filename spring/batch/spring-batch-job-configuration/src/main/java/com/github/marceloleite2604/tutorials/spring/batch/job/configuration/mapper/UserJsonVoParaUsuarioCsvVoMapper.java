package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.LoginJsonVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.NameJsonVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.UserJsonVO;

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
