package com.github.marceloleite2604.tutorials.modelmapper.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.GameDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.validation.HttpPostValidationGroup;
import com.github.marceloleite2604.tutorials.modelmapper.service.GameService;
import com.github.marceloleite2604.tutorials.modelmapper.util.PathUtil;

@Controller
public class GameController {

	@Inject
	private GameService gameService;

	@GetMapping(path = Paths.GAME)
	public String getGame(Model model) {
		return gameService.getGame(model);
	}

	@GetMapping(path = Paths.EDIT)
	public String getGameEdit(@RequestParam(required = false) Integer id, Model model) {
		return gameService.getGameEdit(id, model);
	}

	@PostMapping(path = Paths.EDIT)
	public String postGame(
			@Validated(HttpPostValidationGroup.class) @ModelAttribute("game") GameDTO game,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		return gameService.postGameEdit(game, bindingResult, redirectAttributes, model);
	}

	@PostMapping(path = Paths.DELETE)
	public String deleteGame(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		return gameService.deleteGame(id, redirectAttributes);
	}

	public static final class Paths {

		public static final String GAME = PathUtil.SEPARATOR + "game";

		public static final String EDIT = GAME + PathUtil.SEPARATOR + "edit";

		public static final String DELETE = GAME + PathUtil.SEPARATOR + "delete";

		private Paths() {
			// Private constructor to avoid object instantiation.
		}
	}
}
