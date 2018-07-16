package org.marceloleite.graphs.model;

public class AdjacencyMatrix {

	private int[][] matrix;

	public AdjacencyMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int getSize() {
		return matrix.length;
	}

	public int get(int row, int column) {
		return matrix[row][column];
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int row = 0; row < matrix.length; row++) {
			int[] line = matrix[row];
			for (int column = 0; column < line.length; column++) {
				if (column > 0) {
					stringBuilder.append(" ");
				}
				stringBuilder.append(line[column]);
			}
			stringBuilder.append("\n");
		}
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}
