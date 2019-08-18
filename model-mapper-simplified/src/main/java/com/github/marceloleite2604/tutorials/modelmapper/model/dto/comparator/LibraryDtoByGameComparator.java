package com.github.marceloleite2604.tutorials.modelmapper.model.dto.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;

@Component
public class LibraryDtoByGameComparator implements Comparator<LibraryDTO> {

	@Override
	public int compare(LibraryDTO first, LibraryDTO second) {

		String firstUsername = first.getUser()
				.getUsername();

		String secondUsername = second.getUser()
				.getUsername();

		return firstUsername.compareTo(secondUsername);
	}

}
