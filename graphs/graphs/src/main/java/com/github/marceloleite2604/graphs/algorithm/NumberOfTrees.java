package com.github.marceloleite2604.graphs.algorithm;

import java.util.HashSet;
import java.util.Set;

import com.github.marceloleite2604.graphs.creator.AdjacencyListCreator;
import com.github.marceloleite2604.graphs.model.AdjacencyList;
import com.github.marceloleite2604.graphs.model.AdjacencyMatrix;
import com.github.marceloleite2604.graphs.model.Node;

public class NumberOfTrees {

	private AdjacencyListCreator adjacencyListCreator;

	public NumberOfTrees() {
		adjacencyListCreator = new AdjacencyListCreator();
	}

	public int calculate(AdjacencyMatrix adjacencyMatrix) {
		AdjacencyList adjacencyLists = adjacencyListCreator.create(adjacencyMatrix);
		return countTrees(adjacencyLists);
	}

	private int countTrees(AdjacencyList adjacencyList) {
		int result = 0;
		Set<Node> visited = new HashSet<>();
		for (Node node : adjacencyList.getNodes()) {
			doPath(node, visited);
			result++;
		}
		return result;
	}

	private void doPath(Node node, Set<Node> visited) {
		visited.add(node);

		for (Node adjacent : node.getAdjacents()) {
			if (!visited.contains(adjacent)) {
				doPath(adjacent, visited);
			}
		}
	}
}
