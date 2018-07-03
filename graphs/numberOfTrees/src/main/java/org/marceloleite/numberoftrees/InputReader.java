package org.marceloleite.numberoftrees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {

	private static final String DIRECTORY_PATH = "src/main/resources/";

	public int[][] read(String fileName) {
		int[][] result;
		String path = DIRECTORY_PATH + fileName;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			int matrixDimension = Integer.parseInt(bufferedReader.readLine());
			result = new int[matrixDimension][matrixDimension];
			
			for (int row = 0; row < matrixDimension; row++) {
				String line = bufferedReader.readLine();
				for (int column = 0; column < matrixDimension; column++) {
					result[row][column] = Integer.parseInt(line.substring(column, column+1));
				}	
			}
		} catch (IOException exception) {
			throw new RuntimeException("Error while reading file \"" + path + "\".", exception);
		}
		return result;
	}
}
