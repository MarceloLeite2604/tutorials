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

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.validation.HttpPostValidationGroup;
import com.github.marceloleite2604.tutorials.modelmapper.service.LibraryService;
import com.github.marceloleite2604.tutorials.modelmapper.util.PathUtil;

@Controller
public class LibraryController {

	@Inject
	private LibraryService libraryService;

	@GetMapping(path = Paths.BY_USER)
	public String getLibraryUser(Model model) {
		return libraryService.getLibraryUser(model);
	}

	@GetMapping(path = Paths.BY_GAME)
	public String getLibraryGame(Model model) {
		return libraryService.getLibraryGame(model);
	}

	@GetMapping(path = Paths.EDIT)
	public String getLibraryEdit(@RequestParam(required = false) String libraryId,
			@RequestParam(required = false) String userId,
			@RequestParam(required = false) Integer gameId, @RequestParam String previousPage,
			Model model) {
		return libraryService.getLibraryEdit(libraryId, userId, gameId, previousPage, model);
	}

	@PostMapping(path = Paths.EDIT)
	public String postLibraryEdit(
			@Validated(HttpPostValidationGroup.class) @ModelAttribute("library") LibraryDTO library,
			@RequestParam String previousPage, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		return libraryService.postLibraryEdit(library, previousPage, bindingResult,
				redirectAttributes, model);
	}

	@PostMapping(path = Paths.DELETE)
	public String postLibraryDelete(@RequestParam(required = true) String libraryId,
			RedirectAttributes redirectAttributes) {
		return libraryService.postLibraryDelete(libraryId, redirectAttributes);
	}

	public static final class Paths {

		public static final String LIBRARY = PathUtil.SEPARATOR + "library";

		public static final String BY_USER = LIBRARY + UserController.Paths.USER;

		public static final String BY_GAME = LIBRARY + GameController.Paths.GAME;

		public static final String DELETE = LIBRARY + PathUtil.SEPARATOR + "delete";

		public static final String EDIT = LIBRARY + PathUtil.SEPARATOR + "edit";

		private Paths() {
			// Private constructor to avoid object instantiation.
		}
	}
}