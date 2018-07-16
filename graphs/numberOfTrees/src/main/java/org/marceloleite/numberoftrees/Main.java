package org.marceloleite.numberoftrees;

public class Main {
	
	public static void main(String[] args) {
		int[][] matrix = new InputReader().read("input19.txt");
		printMatrix(matrix);
		System.out.println("Number of trees: " + new NumberOfTrees().calculate(matrix));
	}
	
	public static void printMatrix(int[][] matrix) {
		System.out.println("Matrix:");
		for (int row = 0; row < matrix.length; row++) {
			int[] line = matrix[row];
			for (int column = 0; column < line.length; column++) {
				System.out.print(line[column] + " ");
			}	
			System.out.println();
		}
		System.out.println();
	}
}
