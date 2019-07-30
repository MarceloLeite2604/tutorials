package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.dao.UserDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class UserBO {

	@Inject
	@Lazy
	private ModelMapper modelMapper;

	@Inject
	private UserDAO userDAO;

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
		UserPO userPO = modelMapper.map(user, UserPO.class);
		UserPO savedUserPO = save(userPO);
		UserDTO savedUserDTO = modelMapper.map(savedUserPO, UserDTO.class);
		return savedUserDTO;
	}

	public UserPO save(UserPO user) {
		return userDAO.save(user);
	}

	public void delete(UserPO user) {
		userDAO.delete(user);
	}

	public boolean isNew(UserDTO user) {
		return StringUtils.isBlank(user.getId());
	}

	public boolean isNew(UserPO user) {
		return Objects.isNull(user.getId());
	}
	
	public List<UserPO> findAll() {
		return userDAO.findAll();
	}

	public Optional<UserPO> findById(UUID id) {
		return userDAO.findById(id);
	}

	public UserPO findMandatoryById(UUID id) {
		return userDAO.findMandatoryById(id);
	}
	
	public List<UserDTO> mapAsDto(List<UserPO> userPOs) {
		List<UserDTO> userDTOs =  new ArrayList<>(userPOs.size());
		for (UserPO userPO : userPOs) {
			userDTOs.add(mapAsDto(userPO));
		}
		return userDTOs;
	}

	public UserDTO mapAsDto(UserPO gamePO) {
		return modelMapper.map(gamePO, UserDTO.class);
	}
	
	public UserPO mapAsPo(UserDTO gameDTO) {
		return modelMapper.map(gameDTO, UserPO.class);
	}
}
