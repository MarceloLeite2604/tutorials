package com.github.marceloleite2604.tutorials.modelmapper.service;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marceloleite2604.tutorials.modelmapper.bo.GameBO;
import com.github.marceloleite2604.tutorials.modelmapper.bo.LibraryBO;
import com.github.marceloleite2604.tutorials.modelmapper.bo.UserBO;
import com.github.marceloleite2604.tutorials.modelmapper.controller.LibraryController;
import com.github.marceloleite2604.tutorials.modelmapper.controller.LibraryController.Paths;
import com.github.marceloleite2604.tutorials.modelmapper.controller.UserController;
import com.github.marceloleite2604.tutorials.modelmapper.model.ThymeleafModelAttributeNames;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.LibraryMessage;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.UserMessage;

@Component
public class LibraryService extends AbstractService {

	@Inject
	private UserBO userBO;

	@Inject
	private GameBO gameBO;

	@Inject
	private LibraryBO libraryBO;

	public String getLibraryUser(Model model) {
		addUsersAndLibrariesOnModel(model);
		return Templates.LIBRARY_USER;
	}

	private void addUsersAndLibrariesOnModel(Model model) {
		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAll());
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);
		addUsersOnModel(model, libraries);
	}

	private void addUsersOnModel(Model model) {
		addUsersOnModel(model, null);
	}

	private void addUsersOnModel(Model model, List<LibraryDTO> libraries) {
		List<UserDTO> users;

		if (Objects.isNull(libraries)) {
			users = userBO.mapAsDto(userBO.findAll());
		} else {
			users = libraryBO.retrieveUsers(libraries);
		}

		Collections.sort(users);
		model.addAttribute(ThymeleafModelAttributeNames.USERS, users);
	}

	public String getLibraryGame(Model model) {
		addGamesAndLibrariesOnModel(model);
		return Templates.LIBRARY_GAME;
	}

	private void addGamesAndLibrariesOnModel(Model model) {
		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAll());
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);

		addGamesOnModel(model, libraries);
	}

	private void addGamesOnModel(Model model) {
		addGamesOnModel(model, null);
	}

	private void addGamesOnModel(Model model, List<LibraryDTO> libraries) {
		List<GameDTO> games;

		if (Objects.isNull(libraries)) {
			games = gameBO.mapAsDto(gameBO.findAll());
		} else {
			games = libraryBO.retrieveGames(libraries);
		}

		Collections.sort(games);
		model.addAttribute(ThymeleafModelAttributeNames.GAMES, games);
	}

	public String getLibraryEdit(String libraryId, String userId, Integer gameId,
			String previousPage, Model model) {
		LibraryDTO library = createOrRetrieveLibrary(libraryId);

		if (StringUtils.isNotBlank(userId)) {
			addUserOnModelAndLibrary(userId, model, library);
		} else {
			addUsersOnModel(model);
		}

		if (!Objects.isNull(gameId)) {
			addGameOnModelAndLibrary(gameId, model, library);
		} else {
			addGamesOnModel(model);
		}

		model.addAttribute(ThymeleafModelAttributeNames.PREVIOUS_PAGE, previousPage);
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARY, library);
		return Templates.LIBRARY_EDIT;
	}

	private void addUserOnModelAndLibrary(String userId, Model model, LibraryDTO library) {
		UUID userUuid = UUID.fromString(userId);
		UserDTO user = userBO.mapAsDto(userBO.findMandatoryById(userUuid));
		library.setUser(user);
		model.addAttribute(ThymeleafModelAttributeNames.USER, user);
	}

	private void addGameOnModelAndLibrary(Integer gameId, Model model, LibraryDTO library) {
		GameDTO game = gameBO.mapAsDto(gameBO.findMandatoryById(gameId));
		library.setGame(game);
		model.addAttribute(ThymeleafModelAttributeNames.USER, game);
	}

	private LibraryDTO createOrRetrieveLibrary(String id) {
		return Optional.ofNullable(id)
				.map(this::retrieveLibrary)
				.orElseGet(LibraryDTO::new);
	}

	private LibraryDTO retrieveLibrary(String id) {
		LibraryDTO libraryDTO;
		UUID uuid = UUID.fromString(id);
		LibraryPO libraryPO = libraryBO.findMandatoryById(uuid);
		libraryDTO = libraryBO.mapAsDto(libraryPO);
		return libraryDTO;
	}

	public String postLibraryEdit(LibraryDTO library, String previousPage,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ThymeleafModelAttributeNames.LIBRARY, library);
			return Templates.LIBRARY_EDIT;
		}

		LibraryPO libraryPO = libraryBO.mapAsPo(library);

		libraryPO = libraryBO.save(libraryPO);

		LibraryMessage libraryMessage;
		if (libraryBO.isNew(library)) {
			libraryMessage = LibraryMessage.CREATED;
		} else {
			libraryMessage = LibraryMessage.MODIFIED;
		}

		String gameName = libraryPO.getGame()
				.getName();

		String username = libraryPO.getUser()
				.getUsername();

		serviceUtil.addInformationMessage(redirectAttributes, libraryMessage, gameName, username);

		if (Paths.BY_GAME.contentEquals(previousPage)) {
			return serviceUtil.redirectTo(LibraryController.Paths.BY_GAME);
		}

		return serviceUtil.redirectTo(LibraryController.Paths.BY_GAME);
	}

	public String postLibraryDelete(String id, RedirectAttributes redirectAttributes) {
		LibraryPO library = libraryBO.findMandatoryById(UUID.fromString(id));
		libraryBO.delete(library);

		String gameName = library.getGame()
				.getName();

		String username = library.getUser()
				.getUsername();

		serviceUtil.addInformationMessage(redirectAttributes, UserMessage.DELETED, gameName,
				username);
		return serviceUtil.redirectTo(UserController.Paths.USER);
	}

	static final class Templates {

		public static final String LIBRARY_DIRECTORY = "library" + File.separator;

		private static final String LIBRARY_USER = LIBRARY_DIRECTORY + "library-user";

		private static final String LIBRARY_GAME = LIBRARY_DIRECTORY + "library-game";

		private static final String LIBRARY_EDIT = LIBRARY_DIRECTORY + "library-edit";

		private Templates() {
			// Private constructor to avoid object instantiation.
		}
	}

}
