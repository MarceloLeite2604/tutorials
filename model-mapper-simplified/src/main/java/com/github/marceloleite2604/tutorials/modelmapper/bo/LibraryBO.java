package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.marceloleite2604.tutorials.modelmapper.dao.LibraryDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.comparator.LibraryDtoByGameComparator;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.comparator.LibraryDtoByUserComparator;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;

@Component
public class LibraryBO {

	@Inject
	private LibraryDAO libraryDAO;

	@Inject
	@Lazy
	private ModelMapper modelMapper;

	@Inject
	private LibraryDtoByUserComparator libraryDtoByUserComparator;

	@Inject
	private LibraryDtoByGameComparator libraryDtoByGameComparator;

	public boolean isNew(LibraryDTO library) {
		return StringUtils.isBlank(library.getId());
	}

	public LibraryPO save(LibraryPO library) {
		return libraryDAO.save(library);
	}

	public void delete(LibraryPO library) {
		libraryDAO.delete(library);
	}

	public List<UserDTO> retrieveUsers(List<LibraryDTO> libraries) {
		return retrieveDistinct(libraries, LibraryDTO::getUser);
	}

	public List<LibraryDTO> retrieveByGame(List<LibraryDTO> libraries, GameDTO game) {
		return retrieveByPredicate(libraries, library -> library.getGame()
				.getId()
				.equals(game.getId()));
	}

	public List<LibraryDTO> retrieveByUser(List<LibraryDTO> libraries, UserDTO user) {
		return retrieveByPredicate(libraries, library -> library.getUser()
				.getId()
				.equals(user.getId()));
	}

	public List<LibraryDTO> retrieveByPredicate(List<LibraryDTO> libraries,
			Predicate<? super LibraryDTO> predicate) {
		return libraries.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}

	public List<GameDTO> retrieveGames(List<LibraryDTO> libraries) {
		return retrieveDistinct(libraries, LibraryDTO::getGame);
	}

	public <T> List<T> retrieveDistinct(List<LibraryDTO> libraries,
			Function<? super LibraryDTO, T> function) {
		return libraries.stream()
				.map(function)
				.distinct()
				.collect(Collectors.toList());
	}

	public List<LibraryPO> findAllByUserId(UUID userId) {
		return libraryDAO.findAllByUserId(userId);
	}

	public List<LibraryPO> findAllByGameId(int gameId) {
		return libraryDAO.findAllByGameId(gameId);
	}

	public void sortByUser(List<LibraryDTO> libraries) {
		Collections.sort(libraries, libraryDtoByUserComparator);
	}

	public void sortByGame(List<LibraryDTO> libraries) {
		Collections.sort(libraries, libraryDtoByGameComparator);
	}

	public List<LibraryPO> findAll() {
		return libraryDAO.findAll();
	}

	public List<LibraryDTO> mapAsDto(List<LibraryPO> libraries) {

		if (CollectionUtils.isEmpty(libraries)) {
			return Collections.emptyList();
		}

		List<LibraryDTO> libraryDTOs = new ArrayList<>(libraries.size());
		for (LibraryPO libraryPO : libraries) {
			LibraryDTO libraryDTO = mapAsDto(libraryPO);
			libraryDTOs.add(libraryDTO);
		}

		return libraryDTOs;
	}

	public LibraryDTO mapAsDto(LibraryPO library) {
		return modelMapper.map(library, LibraryDTO.class);
	}

	public LibraryPO findMandatoryById(UUID id) {
		return libraryDAO.findMandatoryById(id);
	}

	public LibraryPO mapAsPo(LibraryDTO library) {
		return modelMapper.map(library, LibraryPO.class);
	}
}
