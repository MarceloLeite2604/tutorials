package com.github.marceloleite2604.tutorials.modelmapper.bo;

import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.configuration.BeanNames;
import com.github.marceloleite2604.tutorials.modelmapper.dao.AbstractDAO;
import com.github.marceloleite2604.tutorials.modelmapper.dao.UserDAO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.PoToDtoMapper;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class UserBO extends AbstractBO<UUID, UserPO, UserDTO> {

	@Inject
	@Named(BeanNames.USER_PO_TO_DTO_MAPPER)
	private PoToDtoMapper<UserPO, UserDTO> poToDtoMapper;

	@Inject
	private UserDAO userDAO;

	@Override
	protected PoToDtoMapper<UserPO, UserDTO> getPoToDtoMapper() {
		return poToDtoMapper;
	}

	@Override
	protected AbstractDAO<UserPO, UUID> getDAO() {
		return userDAO;
	}

	@Bean(BeanNames.USER_PO_TO_DTO_MAPPER)
	public PoToDtoMapper<UserPO, UserDTO> createPoToDtoMapper(ModelMapper modelMapper) {
		return new PoToDtoMapper<>(modelMapper, UserPO.class, UserDTO.class);
	}

	public boolean isPasswordValid(UserDTO user) {

		if (Objects.isNull(user)) {
			return true;
		}

		if (!Objects.isNull(user.getId())) {
			return true;
		}

		return !StringUtils.isBlank(user.getPassword());
	}

	@Override
	public UserDTO save(UserDTO user) {

		if (isNew(user)) {
			user.setDeleted(false);
			user.setEnabled(true);
		}

		return super.save(user);
	}

	@Override
	public void delete(UserPO entity) {
		super.delete(entity);
	}

	public boolean isNew(UserDTO user) {
		return StringUtils.isBlank(user.getId());
	}
}
