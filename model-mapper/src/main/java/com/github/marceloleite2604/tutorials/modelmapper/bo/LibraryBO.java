package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.configuration.BeanNames;
import com.github.marceloleite2604.tutorials.modelmapper.dao.AbstractDAO;
import com.github.marceloleite2604.tutorials.modelmapper.dao.LibraryDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.PoToDtoMapper;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;

@Component
public class LibraryBO extends AbstractBO<UUID, LibraryPO, LibraryDTO> {

	@Inject
	private LibraryDAO libraryDAO;

	@Inject
	@Named(BeanNames.LIBRARY_PO_TO_DTO_MAPPER)
	private PoToDtoMapper<LibraryPO, LibraryDTO> poToDtoMapper;

	@Override
	protected PoToDtoMapper<LibraryPO, LibraryDTO> getPoToDtoMapper() {
		return poToDtoMapper;
	}

	@Override
	protected AbstractDAO<LibraryPO, UUID> getDAO() {
		return libraryDAO;
	}

	public boolean isNew(LibraryDTO library) {
		return StringUtils.isBlank(library.getId());
	}

	@Override
	public LibraryPO save(LibraryPO po) {
		// TODO Auto-generated method stub
		return super.save(po);
	}

	@Override
	public void delete(LibraryPO entity) {
		super.delete(entity);
	}

	@Bean(BeanNames.LIBRARY_PO_TO_DTO_MAPPER)
	public PoToDtoMapper<LibraryPO, LibraryDTO> createPoToDtoMapper(ModelMapper modelMapper) {
		return new PoToDtoMapper<>(modelMapper, LibraryPO.class, LibraryDTO.class);
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
}
