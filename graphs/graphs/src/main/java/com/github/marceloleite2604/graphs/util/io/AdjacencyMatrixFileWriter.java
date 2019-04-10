package com.github.marceloleite2604.graphs.util.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.github.marceloleite2604.graphs.model.AdjacencyMatrix;
import com.github.marceloleite2604.graphs.util.FileUtil;

public class AdjacencyMatrixFileWriter {

	public void writeMatrixToFile(AdjacencyMatrix adjacencyMatrix, String filePath) {
		FileUtil.throwErrorIfFileExists(filePath);

		File outputFile = new File(filePath);

		try (PrintWriter printWriter = new PrintWriter(outputFile)) {
			printWriter.write(adjacencyMatrix.getSize() + "\n");
			writeMatrixToFile(adjacencyMatrix, printWriter);
		} catch (IOException exception) {
			throw new RuntimeException("Error while writing to file \"" + outputFile.getPath() + "\".", exception);
		}
	}

	private void writeMatrixToFile(AdjacencyMatrix adjacencyMatrix, PrintWriter printWriter) {
		for (int row = 0; row < adjacencyMatrix.getSize(); row++) {
			for (int column = 0; column < adjacencyMatrix.getSize(); column++) {
				printWriter.write(Integer.toString(adjacencyMatrix.get(row, column)));
			}
			printWriter.write("\n");
		}
	}
}
