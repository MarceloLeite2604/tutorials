package org.marceloleite.numberoftrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NumberOfTrees {

	public int calculate(int[][] matrix) {
		Map<Integer, List<Integer>> adjacencyLists = createAdjacencyLists(matrix);
		printAdjacencyLists(adjacencyLists);
		return countTrees(adjacencyLists);
	}

	private Map<Integer, List<Integer>> createAdjacencyLists(int[][] matrix) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int row = 0; row < matrix.length; row++) {

			List<Integer> adjacencyList = new LinkedList<>();
			int[] line = matrix[row];
			for (int column = 0; column < line.length; column++) {
				if (row != column && matrix[row][column] == 1) {
					adjacencyList.add(column);
				}
			}
			result.put(row, adjacencyList);
		}
		return result;
	}

	private int countTrees(Map<Integer, List<Integer>> adjacencyLists) {
		int result = 0;
		int totalLists = adjacencyLists.keySet()
				.size();
		List<Boolean> visitedList = createVisitedList(totalLists);
		for (Integer node : adjacencyLists.keySet()) {
			if (!visitedList.get(node)) {
				dfs(node, adjacencyLists, visitedList);
				result++;
			}
		}
		return result;
	}

	private void dfs(Integer node, Map<Integer, List<Integer>> adjacencyLists, List<Boolean> visitedList) {
		visitedList.set(node, true);
		List<Integer> adjacencyList = adjacencyLists.get(node);
		for (Integer neighbour : adjacencyList) {
			if (!visitedList.get(neighbour)) {
				dfs(neighbour, adjacencyLists, visitedList);
			}
		}
	}

	private List<Boolean> createVisitedList(int totalLists) {
		List<Boolean> visited = new ArrayList<>(totalLists);
		for (int index = 0; index < totalLists; index++) {
			visited.add(false);
		}
		return visited;
	}

	private void printAdjacencyLists(Map<Integer, List<Integer>> adjacencyLists) {
		System.out.println("Adjacency lists:");
		for (Integer node : adjacencyLists.keySet()) {
			System.out.print(node + ": ");
			List<Integer> adjacencyList = adjacencyLists.get(node);
			for (Integer neighbour : adjacencyList) {
				System.out.print(neighbour + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
