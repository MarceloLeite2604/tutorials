package org.marceloleite.bfsdfs;

import java.util.HashSet;

public class DepthFirstSearch {

	public boolean hasPath(Node source, Node destination, HashSet<Integer> visited) {
		if (visited.contains(source.getId())) {
			return false;
		}
		
		visited.add(source.getId());
		
		if (source == destination) {
			return true;
		}
		
		for (Node adjacent : source.getAdjacents()) {
			if (hasPath(adjacent, destination, visited)) {
				return true;
			}
		}
		return false;
	}
}
