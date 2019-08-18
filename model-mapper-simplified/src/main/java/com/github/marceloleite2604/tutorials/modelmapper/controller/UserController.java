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
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;
import com.github.marceloleite2604.tutorials.modelmapper.model.validation.HttpPostValidationGroup;
import com.github.marceloleite2604.tutorials.modelmapper.service.UserService;
import com.github.marceloleite2604.tutorials.modelmapper.util.PathUtil;

@Controller
public class UserController {

	@Inject
	private UserService userService;

	@GetMapping(path = Paths.USER)
	public String getGame(Model model) {
		return userService.getUser(model);
	}
	
	@GetMapping(path = Paths.EDIT)
	public String getUserEdit(@RequestParam(required = false) String id, Model model) {
		return userService.getUserEdit(id, model);
	}

	@PostMapping(path = Paths.EDIT)
	public String postUserEdit(@Validated(HttpPostValidationGroup.class) @ModelAttribute("user") UserDTO user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		return userService.postUserEdit(user, bindingResult, redirectAttributes, model);
	}
	
	@PostMapping(path = Paths.EDIT_PO)
	public String postUserPO(@Validated(HttpPostValidationGroup.class) @ModelAttribute("user") UserPO user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		return userService.postUserPo(user, bindingResult, redirectAttributes, model);
	}
	
	@PostMapping(path = Paths.DELETE)
	public String deleteUser(@RequestParam String id, RedirectAttributes redirectAttributes) {
		return userService.deleteUser(id, redirectAttributes);
	}

	public static final class Paths {
		
		public static final String USER = PathUtil.SEPARATOR + "user";
		
		public static final String EDIT = USER + PathUtil.SEPARATOR + "edit";
		
		public static final String EDIT_PO = USER + PathUtil.SEPARATOR + "edit-po";
		
		public static final String DELETE = USER + PathUtil.SEPARATOR + "delete";

		private Paths() {
			// Private constructor to avoid object instantiation.
		}
	}
}
