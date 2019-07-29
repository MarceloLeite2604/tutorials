package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.configuration.BeanNames;
import com.github.marceloleite2604.tutorials.modelmapper.dao.AbstractDAO;
import com.github.marceloleite2604.tutorials.modelmapper.dao.GameDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.PoToDtoMapper;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;

@Component
public class GameBO extends AbstractBO<Integer, GamePO, GameDTO> {

	@Inject
	private GameDAO gameDAO;

	@Inject
	@Named(BeanNames.GAME_PO_TO_DTO_MAPPER)
	private PoToDtoMapper<GamePO, GameDTO> poToDtoMapper;

	@Override
	protected AbstractDAO<GamePO, Integer> getDAO() {
		return gameDAO;
	}

	@Override
	protected PoToDtoMapper<GamePO, GameDTO> getPoToDtoMapper() {
		return poToDtoMapper;
	}

	@Override
	public GameDTO save(GameDTO game) {
		return super.save(game);
	}

	@Override
	public void delete(GamePO game) {
		super.delete(game);
	}

	public boolean isNew(GameDTO game) {
		return Objects.isNull(game.getId());
	}

	@Bean(BeanNames.GAME_PO_TO_DTO_MAPPER)
	public PoToDtoMapper<GamePO, GameDTO> createPoToDtoMapper(ModelMapper modelMapper) {
		return new PoToDtoMapper<>(modelMapper, GamePO.class, GameDTO.class);
	}
}
