package org.marceloleite.adjacencyMatrixGenerator;

import java.util.Comparator;

public class OutputFileNameComparator implements Comparator<String>{

	@Override
	public int compare(String first, String second) {
		int firstNumber = OutputFileUtil.getFileNumber(first);
		int secondNumber = OutputFileUtil.getFileNumber(second);
		return Integer.compare(secondNumber, firstNumber);
	}
}
