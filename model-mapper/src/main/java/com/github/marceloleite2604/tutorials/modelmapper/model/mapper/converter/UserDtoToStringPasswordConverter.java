package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter;

import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.bo.UserBO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class UserDtoToStringPasswordConverter implements Converter<UserDTO, String> {

	@Inject
	@Lazy
	private UserBO userBO;

	@Override
	public String convert(MappingContext<UserDTO, String> context) {
		UserDTO userDTO = context.getSource();

		if (Objects.isNull(userDTO)) {
			return null;
		}

		if (!userBO.isNew(userDTO)) {
			UUID uuid = UUID.fromString(userDTO.getId());
			UserPO userPO = userBO.findMandatoryById(uuid);
			return userPO.getPassword();
		}

		return userDTO.getPassword();
	}

}
