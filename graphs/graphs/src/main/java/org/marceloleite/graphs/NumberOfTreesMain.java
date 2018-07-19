package org.marceloleite.graphs;

import java.util.List;

import org.marceloleite.graphs.algorithm.NumberOfTrees;
import org.marceloleite.graphs.model.AdjacencyMatrix;
import org.marceloleite.graphs.util.io.AdjacencyMatrixDirectoryReader;

public class NumberOfTreesMain {
	
	public static void main(String[] args) {
		List<AdjacencyMatrix> adjacencyMatrices = new AdjacencyMatrixDirectoryReader().read();
		NumberOfTrees numberOfTrees = new NumberOfTrees();
		for (AdjacencyMatrix adjacencyMatrix : adjacencyMatrices) {
			System.out.println("Number of trees: " + numberOfTrees.calculate(adjacencyMatrix));	
		}
	}
}
