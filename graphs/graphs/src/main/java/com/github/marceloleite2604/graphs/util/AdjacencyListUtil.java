package com.github.marceloleite2604.graphs.util;

import java.util.List;
import java.util.Map;

public class AdjacencyListUtil {

	public static void printAdjacencyLists(Map<Integer, List<Integer>> adjacencyLists) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Integer node : adjacencyLists.keySet()) {
			stringBuilder.append(node + ": ");
			List<Integer> adjacencyList = adjacencyLists.get(node);
			for (Integer neighbour : adjacencyList) {
				stringBuilder.append(neighbour + " ");
			}
			stringBuilder.append("\n");
		}
	}
}
