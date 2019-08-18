package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.marceloleite2604.tutorials.modelmapper.dao.GameDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;

@Component
public class GameBO {

	@Inject
	private GameDAO gameDAO;

	@Inject
	@Lazy
	private ModelMapper modelMapper;

	public GameDTO save(GameDTO game) {
		GamePO gamePO = mapAsPo(game);
		GamePO savedGamePO = gameDAO.save(gamePO);
		return mapAsDto(savedGamePO);
	}

	public void delete(GamePO game) {
		gameDAO.delete(game);
	}

	public boolean isNew(GameDTO game) {
		return Objects.isNull(game.getId());
	}

	private GamePO mapAsPo(GameDTO game) {
		return modelMapper.map(game, GamePO.class);
	}

	public GameDTO mapAsDto(GamePO game) {
		return modelMapper.map(game, GameDTO.class);
	}

	public List<GamePO> findAll() {
		return gameDAO.findAll();
	}

	public GamePO findMandatoryById(Integer id) {
		return gameDAO.findMandatoryById(id);
	}

	public List<GameDTO> mapAsDto(List<GamePO> games) {

		if (CollectionUtils.isEmpty(games)) {
			return Collections.emptyList();
		}

		List<GameDTO> gameDTOs = new ArrayList<>(games.size());
		for (GamePO gamePO : games) {
			GameDTO gameDTO = mapAsDto(gamePO);
			gameDTOs.add(gameDTO);
		}

		return gameDTOs;
	}
}
