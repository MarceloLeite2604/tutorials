package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.aquisicaousuarios;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.mapper.UserJsonVoParaUsuarioCsvVoMapper;
import com.github.marceloleite2604.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.job.configuration.model.json.UserJsonVO;

@Component
public class AquisicaoUsuariosItemProcessor implements ItemProcessor<UserJsonVO, UsuarioCsvVO> {

	@Inject
	private UserJsonVoParaUsuarioCsvVoMapper userJsonVoParaUsuarioCsvVoMapper;

	@Override
	public UsuarioCsvVO process(UserJsonVO userJsonVO) throws Exception {
		return userJsonVoParaUsuarioCsvVoMapper.map(userJsonVO);
	}

}
