package com.github.marceloleite2604.tutorials.modelmapper.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marceloleite2604.tutorials.modelmapper.bo.UserBO;
import com.github.marceloleite2604.tutorials.modelmapper.controller.UserController;
import com.github.marceloleite2604.tutorials.modelmapper.model.ThymeleafModelAttributeNames;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.UserMessage;

@Component
public class UserService extends AbstractService {

	@Inject
	private UserBO userBO;

	public String getUser(Model model) {
		addUsersOnModel(model);
		return Templates.USER;
	}

	private void addUsersOnModel(Model model) {
		List<UserPO> users = userBO.findAll();
		model.addAttribute(ThymeleafModelAttributeNames.USERS, userBO.mapAsDto(users));
	}

	public String getUserEdit(String id, Model model) {
		UserDTO user = createOrRetrieveUser(id);
		model.addAttribute(ThymeleafModelAttributeNames.USER, user);
		return Templates.USER_EDIT;
	}

	private UserDTO createOrRetrieveUser(String id) {
		return Optional.ofNullable(id)
				.map(this::retrieveUser)
				.orElseGet(UserDTO::new);
	}

	private UserDTO retrieveUser(String id) {
		UserDTO userDTO;
		UUID uuid = UUID.fromString(id);
		UserPO userPO = userBO.findMandatoryById(uuid);
		userDTO = userBO.mapAsDto(userPO);
		return userDTO;
	}

	public String postUserEdit(UserDTO user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {

		checkPasswordIsValid(user, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute(ThymeleafModelAttributeNames.USER, user);
			return Templates.USER_EDIT;
		}

		userBO.save(user);

		UserMessage userMessage;
		if (userBO.isNew(user)) {
			userMessage = UserMessage.CREATED;
		} else {
			userMessage = UserMessage.MODIFIED;
		}

		serviceUtil.addInformationMessage(redirectAttributes, userMessage,
				user.getUsername());

		return serviceUtil.redirectTo(UserController.Paths.USER);
	}

	public String postUserPo(UserPO user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		
		checkPasswordIsValid(user, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute(ThymeleafModelAttributeNames.USER, user);
			return Templates.USER_EDIT;
		}

		userBO.save(user);

		UserMessage userMessage;
		if (userBO.isNew(user)) {
			userMessage = UserMessage.CREATED;
		} else {
			userMessage = UserMessage.MODIFIED;
		}

		serviceUtil.addInformationMessage(redirectAttributes, userMessage,
				user.getUsername());

		return serviceUtil.redirectTo(UserController.Paths.USER);
	}
	
	private void checkPasswordIsValid(UserPO user, BindingResult bindingResult) {
		checkPasswordIsValid(userBO.mapAsDto(user), bindingResult);
	}

	private void checkPasswordIsValid(UserDTO user, BindingResult bindingResult) {
		if (!userBO.isPasswordValid(user)) {
			FieldError fieldError = new FieldError(ThymeleafModelAttributeNames.USER, "password",
					messageLoader.getMessage(UserMessage.PASSWORD_NOT_BLANK));
			bindingResult.addError(fieldError);
		}
	}

	public String deleteUser(String id, RedirectAttributes redirectAttributes) {
		UserPO user = userBO.findMandatoryById(UUID.fromString(id));
		userBO.delete(user);
		serviceUtil.addInformationMessage(redirectAttributes, UserMessage.DELETED, user.getUsername());
		return serviceUtil.redirectTo(UserController.Paths.USER);
	}

	static final class Templates {

		public static final String USER_DIRECTORY = "user" + File.separator;

		private static final String USER = USER_DIRECTORY + "user";

		private static final String USER_EDIT = USER_DIRECTORY + "user-edit";

		private Templates() {
			// Private constructor to avoid object instantiation.
		}
	}
}
