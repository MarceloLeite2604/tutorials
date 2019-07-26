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

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.validation.HttpPostValidationGroup;
import com.github.marceloleite2604.tutorials.modelmapper.service.UserService;
import com.github.marceloleite2604.tutorials.modelmapper.util.PathUtil;

@Controller
public class UserController {

	@Inject
	private UserService userService;

	@GetMapping(path = Paths.USER)
	public String getUser(@RequestParam(required = false) String id, Model model) {
		return userService.getUser(id, model);
	}

	@PostMapping(path = Paths.USER)
	public String postUser(@Validated(HttpPostValidationGroup.class) @ModelAttribute("user") UserDTO user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		return userService.postUser(user, bindingResult, redirectAttributes, model);
	}

	public static final class Paths {
		public static final String USER = PathUtil.SEPARATOR + "user";

		private Paths() {
			// Private constructor to avoid object instantiation.
		}
	}
}
