package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.dao.GameDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;

@Component
public class GameBO {

	@Inject
	@Lazy
	private GameDAO gameDAO;

	@Inject
	private ModelMapper modelMapper;

	public GameDTO save(GameDTO game) {
		GamePO gamePO = modelMapper.map(game, GamePO.class);
		GamePO savedGamePO = save(gamePO);
		GameDTO savedGameDTO = modelMapper.map(savedGamePO, GameDTO.class);
		return savedGameDTO;
	}

	public GamePO save(GamePO game) {
		return gameDAO.save(game);
	}

	public void delete(GamePO game) {
		gameDAO.delete(game);
	}

	public boolean isNew(GameDTO game) {
		return Objects.isNull(game.getId());
	}

	public List<GamePO> findAll() {
		return gameDAO.findAll();
	}

	public GamePO findMandatoryById(Integer id) {
		return gameDAO.findMandatoryById(id);
	}

	public GameDTO mapAsDto(GamePO gamePO) {
		return modelMapper.map(gamePO, GameDTO.class);
	}
}
