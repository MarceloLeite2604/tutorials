package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.bo.AbstractBO;
import com.github.marceloleite2604.tutorials.modelmapper.bo.GameBO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;

@Component
public class IntegerToGamePoConverter extends AbstractIdToPersistentObjectConverter<Integer, GamePO, Integer, GameDTO>{
	
	@Inject
	@Lazy
	private GameBO gameBO;

	@Override
	protected AbstractBO<Integer, GamePO, GameDTO> getBO() {
		return gameBO;
	}

	@Override
	protected Integer convertId(Integer id) {
		return id;
	}

}
