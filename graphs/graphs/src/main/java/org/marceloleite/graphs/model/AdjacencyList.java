package org.marceloleite.graphs.model;

import java.util.List;

public class AdjacencyList {

	private List<Node> nodes;

	public AdjacencyList(List<Node> nodes) {
		super();
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		return nodes;
	}
}
