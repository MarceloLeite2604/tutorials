package com.github.marceloleite2604.graphs.util.io.comparator;

import java.util.Comparator;

import com.github.marceloleite2604.graphs.util.FileUtil;

public class FileNameComparator implements Comparator<String> {

	public int compare(String first, String second) {
		int firstNumber = FileUtil.getFileNumber(first);
		int secondNumber = FileUtil.getFileNumber(second);
		return Integer.compare(secondNumber, firstNumber);
	}
}
