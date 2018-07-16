package org.marceloleite.graphs.util.io;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.marceloleite.graphs.model.AdjacencyMatrix;
import org.marceloleite.graphs.util.FileUtil;

public class AdjacencyMatrixDirectoryReader {

	public List<AdjacencyMatrix> read() {
		return read(FileUtil.DEFAULT_DIRECTORY_PATH);
	}

	public List<AdjacencyMatrix> read(String directoryPath) {
		FileUtil.throwErrorIfFileDoesNotExist(directoryPath);

		File directory = new File(directoryPath);
		AdjacencyMatrixFileReader adjacencyMatrixFileReader = new AdjacencyMatrixFileReader();

		return Arrays.asList(directory.listFiles())
				.stream()
				.filter(file -> FileUtil.INPUT_FILE_NAME_PATTERN.matcher(file.getName())
						.matches())
				.map(File::getAbsolutePath)
				.map(adjacencyMatrixFileReader::read)
				.collect(Collectors.toList());
	}
}
