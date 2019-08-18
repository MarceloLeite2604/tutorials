package com.github.marceloleite2604.tutorials.modelmapper.model.dto.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.LibraryDTO;

@Component
public class LibraryDtoByUserComparator implements Comparator<LibraryDTO> {

	@Override
	public int compare(LibraryDTO first, LibraryDTO second) {

		String firstGame = first.getGame()
				.getName();

		String secondGame = second.getGame()
				.getName();

		return firstGame.compareTo(secondGame);
	}

}
