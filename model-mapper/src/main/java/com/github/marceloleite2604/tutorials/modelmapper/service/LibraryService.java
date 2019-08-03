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
		addUsersAndLibrariesOnModel(model);
		return Templates.USER_SELECT;
	}

	private void addUsersAndLibrariesOnModel(Model model) {
		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAll());
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);
		addUsersOnModel(model, libraries);
	}

	private void addUsersOnModel(Model model) {
		addUsersOnModel(model, null);
	}

	private void addUserOnModel(UUID userId, Model model) {
		UserDTO user = retrieveUser(userId);
		model.addAttribute(ThymeleafModelAttributeNames.USER, user);
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
		return Templates.GAME_SELECT;
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
			String redirectPath, Model model) {

		LibraryDTO library = createOrRetrieveLibrary(libraryId);
		
		if (libraryBO.isNew(library)) {
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
			library = libraryBO.mapAsDto(libraryBO.mapAsPo(library));
		}
		
		addUsersOnModel(model);
		addGamesOnModel(model);

//		if (StringUtils.isNotBlank(actualUserId)) {
//			UUID userUuid = UUID.fromString(actualUserId);
//			addUserOnModelAndLibrary(userUuid, model, library);
//		} else {
//			addUsersOnModel(model);
//		}
//
//		if (!Objects.isNull(actualGameId)) {
//			addGameOnModelAndLibrary(actualGameId, model, library);
//		} else {
//			addGamesOnModel(model);
//		}

		model.addAttribute(ThymeleafModelAttributeNames.REDIRECT_PATH, redirectPath);
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARY, library);
		return Templates.EDIT;
	}

	private void addUserOnModelAndLibrary(UUID userId, Model model, LibraryDTO library) {
		UserDTO user = retrieveUser(userId);
		model.addAttribute(ThymeleafModelAttributeNames.USER, user);
		library.setUser(user);
	}

	private UserDTO retrieveUser(UUID userId) {
		return userBO.mapAsDto(userBO.findMandatoryById(userId));
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

	public String getLibraryUserRecords(String userId, Model model) {
		UUID userUuid = UUID.fromString(userId);
		addUserOnModel(userUuid, model);
		addUserLibrariesOnModel(userUuid, model);
		return Templates.USER;
	}

	private void addUserLibrariesOnModel(UUID userId, Model model) {
		List<LibraryDTO> libraries = libraryBO.mapAsDto(libraryBO.findAllByUserId(userId));
		libraryBO.sortByGame(libraries);
		model.addAttribute(ThymeleafModelAttributeNames.LIBRARIES, libraries);
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
