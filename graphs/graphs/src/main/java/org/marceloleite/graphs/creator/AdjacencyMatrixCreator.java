package org.marceloleite.graphs.creator;

import java.util.concurrent.ThreadLocalRandom;

import org.marceloleite.graphs.model.AdjacencyMatrix;

public class AdjacencyMatrixCreator {

	private final ThreadLocalRandom random = ThreadLocalRandom.current();

	private final int MAX_MATRIX_SIZE = 100;
	private final int MIN_MATRIX_SIZE = 3;

	public AdjacencyMatrix create() {
		int[][] matrix = createEmptyMatrix();
		createConnections(matrix);
		return new AdjacencyMatrix(matrix);
	}

	private void createConnections(int[][] matrix) {
		int numberOfConnections = random.nextInt(0, (matrix.length * 2) + 1);
		int firstNode;
		int secondNode;
		for (int counter = 0; counter < numberOfConnections; counter++) {
			firstNode = random.nextInt(0, matrix.length);
			secondNode = random.nextInt(0, matrix.length);
			matrix[firstNode][secondNode] = 1;
			matrix[secondNode][firstNode] = 1;
		}
	}

	private int[][] createEmptyMatrix() {
		int matrixSize = random.nextInt(MIN_MATRIX_SIZE, MAX_MATRIX_SIZE);
		int[][] result = new int[matrixSize][matrixSize];
		for (int row = 0; row < matrixSize; row++) {
			for (int column = 0; column < matrixSize; column++) {
				result[row][column] = (row == column ? 1 : 0);
			}
		}
		return result;
	}
}
