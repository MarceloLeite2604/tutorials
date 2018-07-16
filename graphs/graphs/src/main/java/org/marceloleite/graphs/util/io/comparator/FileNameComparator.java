package org.marceloleite.graphs.util.io.comparator;

import java.util.Comparator;

import org.marceloleite.graphs.util.FileUtil;

public class FileNameComparator implements Comparator<String> {

	public int compare(String first, String second) {
		int firstNumber = FileUtil.getFileNumber(first);
		int secondNumber = FileUtil.getFileNumber(second);
		return Integer.compare(secondNumber, firstNumber);
	}
}
