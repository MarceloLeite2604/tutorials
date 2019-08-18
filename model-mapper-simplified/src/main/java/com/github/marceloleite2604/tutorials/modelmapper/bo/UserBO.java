package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.marceloleite2604.tutorials.modelmapper.dao.UserDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class UserBO {

	@Inject
	private UserDAO userDAO;

	@Inject
	@Lazy
	private ModelMapper modelMapper;

	public boolean isPasswordValid(UserDTO user) {

		if (Objects.isNull(user)) {
			return true;
		}

		if (!StringUtils.isBlank(user.getId())) {
			return true;
		}

		return !StringUtils.isBlank(user.getPassword());
	}

	public UserDTO save(UserDTO user) {

		UserPO userPO = mapAsPo(user);
		UserPO savedUserPO = save(userPO);
		return mapAsDto(savedUserPO);
	}

	public UserPO save(UserPO user) {

		if (isNew(user)) {
			user.setDeleted(false);
			user.setEnabled(true);
		}

		return userDAO.save(user);
	}

	public void delete(UserPO entity) {
		userDAO.delete(entity);
	}

	public boolean isNew(UserDTO user) {
		return StringUtils.isBlank(user.getId());
	}

	public boolean isNew(UserPO user) {
		return Objects.isNull(user.getId());
	}

	private UserPO mapAsPo(UserDTO user) {
		return modelMapper.map(user, UserPO.class);
	}

	public UserDTO mapAsDto(UserPO user) {
		return modelMapper.map(user, UserDTO.class);
	}

	public UserPO findMandatoryById(UUID id) {
		return userDAO.findMandatoryById(id);
	}

	public List<UserPO> findAll() {
		return userDAO.findAll();
	}

	public List<UserDTO> mapAsDto(List<UserPO> users) {

		if (CollectionUtils.isEmpty(users)) {
			return Collections.emptyList();
		}

		List<UserDTO> userDTOs = new ArrayList<>(users.size());
		for (UserPO userPO : users) {
			UserDTO userDTO = mapAsDto(userPO);
			userDTOs.add(userDTO);
		}

		return userDTOs;
	}
}
