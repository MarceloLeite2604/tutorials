package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.propertymap;

import javax.inject.Inject;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter.IntegerToGamePoConverter;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter.StringToUuidConverter;
import com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter.StringToUserPoConverter;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.LibraryPO;

@Component
public class LibraryDtoToLibraryPoMapper extends PropertyMap<LibraryDTO, LibraryPO> {

	@Inject
	private StringToUserPoConverter uuidToUserPoConverter;

	@Inject
	private IntegerToGamePoConverter integerToGamePoConverter;

	@Inject
	private StringToUuidConverter stringToUuidConverter;

	@Override
	protected void configure() {

		using(stringToUuidConverter).map(source.getId(), destination.getId());

		using(uuidToUserPoConverter).map(source.getUser()
				.getId(), destination.getUser());

		using(integerToGamePoConverter).map(source.getGame()
				.getId(), destination.getGame());
	}

}
