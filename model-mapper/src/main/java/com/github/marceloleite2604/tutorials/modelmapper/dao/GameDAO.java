package com.github.marceloleite2604.tutorials.modelmapper.dao;

import javax.inject.Inject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.dao.repository.GameRepository;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;

@Component
public class GameDAO extends AbstractDAO<GamePO, Integer> {
	
	@Inject
	private GameRepository gameRepository;

	@Override
	protected CrudRepository<GamePO, Integer> getRepository() {
		return gameRepository;
	}

	@Override
	protected Class<GamePO> getPersistentObjectClass() {
		return GamePO.class;
	}
}
