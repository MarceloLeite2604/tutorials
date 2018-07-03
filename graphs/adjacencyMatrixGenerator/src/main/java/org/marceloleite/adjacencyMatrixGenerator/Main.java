package org.marceloleite.adjacencyMatrixGenerator;

public class Main {
	public static void main(String[] args) {
		MatrixCreator matrixCreator = new MatrixCreator();
		OutputWriter outputWriter = new OutputWriter();
		for (int i = 0; i < 20; i++) {
			int[][] matrix = matrixCreator.create();
			outputWriter.writeMatrixToFile(matrix);
		}
	}
}
