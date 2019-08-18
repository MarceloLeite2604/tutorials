package com.github.marceloleite2604.tutorials.modelmapper.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marceloleite2604.tutorials.modelmapper.bo.GameBO;
import com.github.marceloleite2604.tutorials.modelmapper.controller.GameController;
import com.github.marceloleite2604.tutorials.modelmapper.model.ThymeleafModelAttributeNames;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.GamePO;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.GameMessage;

@Component
public class GameService extends AbstractService {

	@Inject
	private GameBO gameBO;

	public String getGame(Model model) {
		addGamesOnModel(model);
		return Templates.GAME;
	}

	private void addGamesOnModel(Model model) {
		List<GamePO> games = gameBO.findAll();
		model.addAttribute(ThymeleafModelAttributeNames.GAMES, games);
	}

	public String getGameEdit(Integer id, Model model) {
		GameDTO game = createOrRetrieveGame(id);
		model.addAttribute(ThymeleafModelAttributeNames.GAME, game);
		return Templates.GAME_EDIT;
	}

	private GameDTO createOrRetrieveGame(Integer id) {
		return Optional.ofNullable(id)
				.map(this::retrieveGame)
				.orElseGet(GameDTO::new);
	}

	private GameDTO retrieveGame(Integer id) {
		GameDTO gameDTO;
		GamePO gamePO = gameBO.findMandatoryById(id);
		gameDTO = gameBO.mapAsDto(gamePO);
		return gameDTO;
	}

	public String postGameEdit(GameDTO game, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ThymeleafModelAttributeNames.GAME, game);
			return Templates.GAME_EDIT;
		}

		gameBO.save(game);

		GameMessage gameMessage;
		if (gameBO.isNew(game)) {
			gameMessage = GameMessage.CREATED;
		} else {
			gameMessage = GameMessage.MODIFIED;
		}

		serviceUtil.addInformationMessage(redirectAttributes, gameMessage, game.getName());
		return serviceUtil.redirectTo(GameController.Paths.GAME);
	}

	public String deleteGame(Integer id, RedirectAttributes redirectAttributes) {
		GamePO game = gameBO.findMandatoryById(id);
		gameBO.delete(game);

		serviceUtil.addInformationMessage(redirectAttributes, GameMessage.DELETED, game.getName());

		return serviceUtil.redirectTo(GameController.Paths.GAME);
	}

	static final class Templates {

		public static final String GAME_DIRECTORY = "game" + File.separator;

		private static final String GAME = GAME_DIRECTORY + "game";

		private static final String GAME_EDIT = GAME_DIRECTORY + "game-edit";

		private Templates() {
			// Private constructor to avoid object instantiation.
		}
	}
}
