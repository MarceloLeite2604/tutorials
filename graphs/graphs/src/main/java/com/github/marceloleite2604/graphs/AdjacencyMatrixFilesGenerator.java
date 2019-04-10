package com.github.marceloleite2604.graphs;

import java.util.ArrayList;
import java.util.List;

import com.github.marceloleite2604.graphs.creator.AdjacencyMatrixCreator;
import com.github.marceloleite2604.graphs.model.AdjacencyMatrix;
import com.github.marceloleite2604.graphs.util.FileUtil;
import com.github.marceloleite2604.graphs.util.io.AdjacencyMatrixDirectoryWriter;

public class AdjacencyMatrixFilesGenerator {
	public static void main(String[] args) {
		new AdjacencyMatrixDirectoryWriter().write(createMatrices(), FileUtil.DEFAULT_DIRECTORY_PATH);
	}

	private static List<AdjacencyMatrix> createMatrices() {
		AdjacencyMatrixCreator matrixCreator = new AdjacencyMatrixCreator();
		int totalLists = 20;
		List<AdjacencyMatrix> adjacencyMatrices = new ArrayList<>(totalLists);
		for (int index = 0; index < totalLists; index++) {
			adjacencyMatrices.add(matrixCreator.create());
		}
		return adjacencyMatrices;
	}
}
