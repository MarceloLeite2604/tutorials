package com.github.marceloleite2604.tutorials.modelmapper.dao;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.dao.repository.LibrariesRepository;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;

@Component
public class LibraryDAO extends AbstractDAO<LibraryPO, UUID> {

	@Inject
	private LibrariesRepository librariesRepository;

	@Override
	protected CrudRepository<LibraryPO, UUID> getRepository() {
		return librariesRepository;
	}

	@Override
	protected Class<LibraryPO> getPersistentObjectClass() {
		return LibraryPO.class;
	}

	public List<LibraryPO> findAllByUserId(UUID userId) {
		return librariesRepository.findAllByUserId(userId);
	}

	public List<LibraryPO> findAllByGameId(int gameId) {
		return librariesRepository.findAllByGameId(gameId);
	}

}
