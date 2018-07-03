package org.marceloleite.adjacencyMatrixGenerator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class OutputWriter {

	private static final String DIRECTORY_PATH = "output/";

	private static final String FIRST_NAME = "input0.txt";

	private static final String NAME_BOILERPLATE = "input%d.txt";

	public void writeMatrixToFile(int[][] matrix) {
		String fileName = createFileName();
		writeFile(matrix, fileName);
	}

	private void writeFile(int[][] matrix, String fileName) {
		File outputFile = new File(DIRECTORY_PATH + fileName);
		try (PrintWriter printWriter = new PrintWriter(outputFile)) {
			printWriter.write(matrix.length + "\n");
			writeMatrixToFile(matrix, printWriter);
		} catch (IOException exception) {
			throw new RuntimeException("Error while writing to file \"" + outputFile.getPath() + "\".", exception);
		}
	}

	private void writeMatrixToFile(int[][] matrix, PrintWriter printWriter) {
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix.length; column++) {
				printWriter.write(matrix[row][column] + "");
			}
			printWriter.write("\n");
		}
	}

	private String createFileName() {
		File outputDirectory = new File(DIRECTORY_PATH);
		String lastFileName = Arrays.asList(outputDirectory.listFiles())
				.stream()
				.map(file -> file.getName())
				.sorted(new OutputFileNameComparator())
				.findFirst()
				.orElse(FIRST_NAME);

		return String.format(NAME_BOILERPLATE, OutputFileUtil.getFileNumber(lastFileName) + 1);
	}
}
