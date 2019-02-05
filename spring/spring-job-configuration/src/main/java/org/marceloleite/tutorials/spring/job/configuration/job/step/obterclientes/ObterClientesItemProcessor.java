package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.mapper.UserJsonVoParaUsuarioCsvVoMapper;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ObterClientesItemProcessor implements ItemProcessor<UserJsonVO, UsuarioCsvVO> {

	@Inject
	private UserJsonVoParaUsuarioCsvVoMapper userJsonVoParaUsuarioCsvVoMapper;

	@Override
	public UsuarioCsvVO process(UserJsonVO userJsonVO) throws Exception {
		return userJsonVoParaUsuarioCsvVoMapper.map(userJsonVO);
	}

}
