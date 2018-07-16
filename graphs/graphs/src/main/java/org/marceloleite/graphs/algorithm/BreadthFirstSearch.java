package org.marceloleite.graphs.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.marceloleite.graphs.model.Node;

public class BreadthFirstSearch {

	public boolean hasPath(Node source, Node destination) {
		Queue<Node> nextToVisit = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		nextToVisit.add(source);
		while (!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			if (node == destination) {
				return true;
			}

			if (!visited.contains(node.getId())) {
				visited.add(node.getId());
				for (Node adjacent : node.getAdjacents()) {
					nextToVisit.add(adjacent);
				}
			}
		}

		return false;
	}
}
