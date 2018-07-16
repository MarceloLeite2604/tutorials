package org.marceloleite.graphs;

import org.marceloleite.graphs.algorithm.NumberOfTrees;
import org.marceloleite.graphs.model.AdjacencyMatrix;
import org.marceloleite.graphs.util.io.AdjacencyMatrixFileReader;

public class NumberOfTreesMain {
	
	public static void main(String[] args) {
		AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrixFileReader().read("input19.txt");
		System.out.println("Number of trees: " + new NumberOfTrees().calculate(adjacencyMatrix));
	}
}
