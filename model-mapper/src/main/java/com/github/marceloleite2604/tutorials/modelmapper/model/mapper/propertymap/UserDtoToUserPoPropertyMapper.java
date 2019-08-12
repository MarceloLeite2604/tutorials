package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.propertymap;

import javax.inject.Inject;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter.StringToUuidConverter;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter.UserDtoToStringPasswordConverter;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@Component
public class UserDtoToUserPoPropertyMapper extends PropertyMap<UserDTO, UserPO> {

	@Inject
	private StringToUuidConverter stringToUuidConverter;

	@Inject
	private UserDtoToStringPasswordConverter userDtoToStringPasswordConverter;

	@Override
	protected void configure() {
		using(stringToUuidConverter).map(source.getId(), destination.getId());
		using(userDtoToStringPasswordConverter).map(source, destination.getPassword());
	}
}
