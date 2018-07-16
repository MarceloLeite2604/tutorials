package org.marceloleite.graphs.creator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import org.marceloleite.graphs.model.AdjacencyList;
import org.marceloleite.graphs.model.AdjacencyMatrix;
import org.marceloleite.graphs.model.Node;

public class AdjacencyListCreator {

	public AdjacencyList create(AdjacencyMatrix adjacencyMatrix) {
		Map<Integer, Node> nodes = createNodesMap(adjacencyMatrix);

		analyseRows(adjacencyMatrix, nodes);
		return new AdjacencyList(nodes.values()
				.stream()
				.collect(Collectors.toList()));
	}

	private Map<Integer, Node> createNodesMap(AdjacencyMatrix adjacencyMatrix) {
		HashMap<Integer, Node> nodes = new HashMap<>(adjacencyMatrix.getSize());
		for (int counter = 0; counter < adjacencyMatrix.getSize(); counter++) {
			nodes.put(counter, new Node(counter, new LinkedList<>()));
		}
		return nodes;
	}

	private void analyseRows(AdjacencyMatrix adjacencyMatrix, Map<Integer, Node> nodes) {
		for (int row = 0; row < adjacencyMatrix.getSize(); row++) {
			analyseColumns(adjacencyMatrix, nodes, row);
		}
	}

	private void analyseColumns(AdjacencyMatrix adjacencyMatrix, Map<Integer, Node> nodes, int row) {
		for (int column = 0; column < adjacencyMatrix.getSize(); column++) {
			if (row != column && adjacencyMatrix.get(row, column) == 1) {
				nodes.get(row)
						.getAdjacents()
						.add(nodes.get(column));
			}
		}
	}
}
