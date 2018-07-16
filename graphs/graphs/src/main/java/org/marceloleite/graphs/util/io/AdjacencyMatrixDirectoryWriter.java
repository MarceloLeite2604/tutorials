package org.marceloleite.graphs.util.io;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.marceloleite.graphs.model.AdjacencyMatrix;
import org.marceloleite.graphs.util.FileUtil;

public class AdjacencyMatrixDirectoryWriter {

	private static final String NAME_BOILERPLATE = "input%d.txt";

	public void write(List<AdjacencyMatrix> adjacencyMatrices, String directoryPath) {
		FileUtil.throwErrorIfFileDoesNotExist(FileUtil.DEFAULT_DIRECTORY_PATH);

		AdjacencyMatrixFileWriter adjacencyMatrixFileWriter = new AdjacencyMatrixFileWriter();

		for (AdjacencyMatrix adjacencyMatrix : adjacencyMatrices) {
			adjacencyMatrixFileWriter.writeMatrixToFile(adjacencyMatrix, createFileName(directoryPath));
		}
	}

	private String createFileName(String directoryPath) {
		FileUtil.throwErrorIfFileDoesNotExist(directoryPath);

		File directory = new File(directoryPath);
		;

		return directoryPath + File.separator + formatFileName(findLastIndex(directory) + 1);
	}

	private int findLastIndex(File directory) {
		return Arrays.asList(directory.listFiles())
				.stream()
				.map(file -> file.getName())
				.map(FileUtil::getFileNumber)
				.sorted((first, second) -> -first.compareTo(second))
				.findFirst()
				.orElse(0);
	}

	private String formatFileName(int index) {
		return String.format(NAME_BOILERPLATE, index);
	}
}
