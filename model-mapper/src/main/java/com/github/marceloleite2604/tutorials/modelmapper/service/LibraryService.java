package com.github.marceloleite2604.tutorials.modelmapper.service;

import java.io.File;
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
import com.github.marceloleite2604.tutorials.modelmapper.model.ThymeleafModelAttributeNames;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.LibraryMessage;

@Component
public class LibraryService extends AbstractService {

	@Inject
	private UserBO userBO;

	@Inject
	private GameBO gameBO;

	@Inject
	private LibraryBO libraryBO;

	public String getLibraryUser(Model model) {
		addLibrariesUsersAndGamesOnModel(model);
		return Templates.USER_SELECT;
	}

	public String getLibraryGame(Model model) {
		addLibrariesUsersAndGamesOnModel(model);
		return Templates.GAME_SELECT;
	}

	private void addLibrariesUsersAndGamesOnModel(Model model) {

		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAll());
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);

		addUsersOnModel(model);
		addGamesOnModel(model);
	}

	public String getLibraryUserRecords(String userId, Model model) {
		UUID userUuid = UUID.fromString(userId);
		addUserOnModel(userUuid, model);
		addUserLibrariesOnModel(userUuid, model);
		return Templates.USER;
	}

	private void addUserOnModel(UUID userId, Model model) {
		UserDTO user = retrieveUser(userId);
		model.addAttribute(ThymeleafModelAttributeNames.USER, user);
	}

	private UserDTO retrieveUser(UUID userId) {
		return userBO.mapAsDto(userBO.findMandatoryById(userId));
	}

	private void addUserLibrariesOnModel(UUID userId, Model model) {
		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAllByUserId(userId));
		libraryBO.sortByGame(libraries);
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);
	}

	public String getLibraryGameRecords(int gameId, Model model) {
		addGameOnModel(gameId, model);
		addGameLibrariesOnModel(gameId, model);
		return Templates.GAME;
	}

	private void addGameOnModel(int gameId, Model model) {
		GameDTO game = retrieveGame(gameId);
		model.addAttribute(ThymeleafModelAttributeNames.GAME, game);
	}

	private GameDTO retrieveGame(int gameId) {
		return gameBO.mapAsDto(gameBO.findMandatoryById(gameId));
	}

	private void addGameLibrariesOnModel(int gameId, Model model) {
		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAllByGameId(gameId));
		libraryBO.sortByUser(libraries);
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);
	}

	public String getLibraryEdit(String libraryId, String userId, Integer gameId,
			String redirectPath, Model model) {

		addLibraryEditAttributesOnModel(libraryId, userId, gameId, redirectPath, model);

		return Templates.EDIT;
	}

	private void addLibraryEditAttributesOnModel(String libraryId, String userId, Integer gameId,
			String redirectPath, Model model) {
		addLibraryOnModel(libraryId, userId, gameId, model);

		if (!StringUtils.isEmpty(userId)) {
			UUID userUuid = UUID.fromString(userId);
			addUserOnModel(userUuid, model);
		} else {
			addUsersOnModel(model);
		}

		if (!Objects.isNull(gameId)) {
				addGameOnModel(gameId, model);
		} else {
			addGamesOnModel(model);
		}

		model.addAttribute(ThymeleafModelAttributeNames.REDIRECT_PATH, redirectPath);
	}

	private void addLibraryOnModel(String libraryId, String userId, Integer gameId, Model model) {
		LibraryDTO library = createOrRetrieveLibrary(libraryId);

		if (libraryBO.isNew(library)) {
			library = populateLibraryDetails(library, userId, gameId);
		}
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARY, library);
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

	private LibraryDTO populateLibraryDetails(LibraryDTO library, String userId, Integer gameId) {

		if (StringUtils.isNotBlank(userId)) {
			UserDTO user = new UserDTO();
			user.setId(userId);
			library.setUser(user);
		}

		if (!Objects.isNull(gameId)) {
			GameDTO game = new GameDTO();
			game.setId(gameId);
			library.setGame(game);
		}

		// Convert to PO and then back to DTO to retrieve user and game properties.
		return libraryBO.mapAsDto(libraryBO.mapAsPo(library));
	}

	private void addUsersOnModel(Model model) {
		List<UserDTO> users = userBO.mapAsDto(userBO.findAll());
		model.addAttribute(ThymeleafModelAttributeNames.USERS, users);
	}

	private void addGamesOnModel(Model model) {
		List<GameDTO> games = gameBO.mapAsDto(gameBO.findAll());
		model.addAttribute(ThymeleafModelAttributeNames.GAMES, games);
	}

	public String postLibraryEdit(LibraryDTO library, String previousPage,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (bindingResult.hasErrors()) {
			addLibraryEditAttributesOnModel(library, previousPage, model);
			return Templates.EDIT;
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

		return serviceUtil.redirectTo(previousPage);
	}

	private void addLibraryEditAttributesOnModel(LibraryDTO library, String redirectPath,
			Model model) {

		String userId = library.getUser()
				.getId();

		Integer gameId = library.getGame()
				.getId();

		addLibraryEditAttributesOnModel(library.getId(), userId, gameId, redirectPath, model);
	}

	public String postLibraryDelete(String id, String redirectPath,
			RedirectAttributes redirectAttributes) {
		LibraryPO library = libraryBO.findMandatoryById(UUID.fromString(id));
		libraryBO.delete(library);

		String gameName = library.getGame()
				.getName();

		String username = library.getUser()
				.getUsername();

		serviceUtil.addInformationMessage(redirectAttributes, LibraryMessage.DELETED, gameName,
				username);
		return serviceUtil.redirectTo(redirectPath);
	}

	static final class Templates {

		public static final String LIBRARY_DIRECTORY = "library" + File.separator;

		private static final String USER_DIRECTORY = LIBRARY_DIRECTORY
				+ UserService.Templates.USER_DIRECTORY;

		public static final String USER_SELECT = USER_DIRECTORY + "library-user-select";

		public static final String USER = USER_DIRECTORY + "library-user";

		private static final String GAME_DIRECTORY = LIBRARY_DIRECTORY
				+ GameService.Templates.GAME_DIRECTORY;

		public static final String GAME_SELECT = GAME_DIRECTORY + "library-game-select";

		public static final String GAME = GAME_DIRECTORY + "library-game";

		public static final String EDIT = LIBRARY_DIRECTORY + "library-edit";

		private Templates() {
			// Private constructor to avoid object instantiation.
		}
	}
}
