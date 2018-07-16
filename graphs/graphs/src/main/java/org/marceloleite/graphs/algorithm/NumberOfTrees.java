package org.marceloleite.graphs.algorithm;

import java.util.HashSet;
import java.util.Set;

import org.marceloleite.graphs.creator.AdjacencyListCreator;
import org.marceloleite.graphs.model.AdjacencyList;
import org.marceloleite.graphs.model.AdjacencyMatrix;
import org.marceloleite.graphs.model.Node;

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
