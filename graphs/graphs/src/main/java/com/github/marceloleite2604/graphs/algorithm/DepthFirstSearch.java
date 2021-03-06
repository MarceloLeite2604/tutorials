package com.github.marceloleite2604.graphs.algorithm;

import java.util.HashSet;

import com.github.marceloleite2604.graphs.model.Node;

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
