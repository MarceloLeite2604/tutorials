package com.github.marceloleite2604.graphs;

import java.util.List;

import com.github.marceloleite2604.graphs.algorithm.NumberOfTrees;
import com.github.marceloleite2604.graphs.model.AdjacencyMatrix;
import com.github.marceloleite2604.graphs.util.io.AdjacencyMatrixDirectoryReader;

public class NumberOfTreesMain {
	
	public static void main(String[] args) {
		List<AdjacencyMatrix> adjacencyMatrices = new AdjacencyMatrixDirectoryReader().read();
		NumberOfTrees numberOfTrees = new NumberOfTrees();
		for (AdjacencyMatrix adjacencyMatrix : adjacencyMatrices) {
			System.out.println("Number of trees: " + numberOfTrees.calculate(adjacencyMatrix));	
		}
	}
}
