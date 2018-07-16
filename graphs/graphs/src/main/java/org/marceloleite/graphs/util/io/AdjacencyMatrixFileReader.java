package org.marceloleite.graphs.util.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.marceloleite.graphs.model.AdjacencyMatrix;

public class AdjacencyMatrixFileReader {

	public AdjacencyMatrix read(String filePath) {
		int[][] matrix;
		String path = filePath;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			int matrixDimension = Integer.parseInt(bufferedReader.readLine());
			matrix = new int[matrixDimension][matrixDimension];

			for (int row = 0; row < matrixDimension; row++) {
				String line = bufferedReader.readLine();
				for (int column = 0; column < matrixDimension; column++) {
					matrix[row][column] = Integer.parseInt(line.substring(column, column + 1));
				}
			}
		} catch (IOException exception) {
			throw new RuntimeException("Error while reading file \"" + path + "\".", exception);
		}
		return new AdjacencyMatrix(matrix);
	}
}
