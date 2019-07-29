package com.github.marceloleite2604.tutorials.modelmapper.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;

public interface GameRepository extends CrudRepository<GamePO, Integer> {

}
