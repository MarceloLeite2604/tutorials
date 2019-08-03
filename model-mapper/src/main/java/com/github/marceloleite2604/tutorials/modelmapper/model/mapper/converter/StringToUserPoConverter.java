package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.bo.AbstractBO;
import com.github.marceloleite2604.tutorials.modelmapper.bo.UserBO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class StringToUserPoConverter
		extends AbstractIdToPersistentObjectConverter<UUID, UserPO, String, UserDTO> {

	@Inject
	@Lazy
	private UserBO userBO;

	@Override
	protected AbstractBO<UUID, UserPO, UserDTO> getBO() {
		return userBO;
	}

	@Override
	protected UUID convertId(String id) {
		return UUID.fromString(id);
	}

}
