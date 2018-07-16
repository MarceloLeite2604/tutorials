package org.marceloleite.graphs.model;

import java.util.List;

public class Node {

	private int id;

	private List<Node> adjacents;

	public Node(int id, List<Node> adjacents) {
		super();
		this.id = id;
		this.adjacents = adjacents;
	}

	public int getId() {
		return id;
	}

	public List<Node> getAdjacents() {
		return adjacents;
	}
}
