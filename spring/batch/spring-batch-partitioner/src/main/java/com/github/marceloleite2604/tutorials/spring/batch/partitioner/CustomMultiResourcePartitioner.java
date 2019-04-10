package com.github.marceloleite2604.tutorials.spring.batch.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class CustomMultiResourcePartitioner implements Partitioner {

	private static final String PARTITION_KEY = "PARTITION_KEY";
	
	private static final String KEY_NAME = "fileName";

	private Resource[] resources;

	public void setResources(Resource[] resources) {
		this.resources = resources;
	}

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> map = new HashMap<>(gridSize);
		int partitionCounter = 0;
		int outputCounter = 0;
		for (Resource resource : resources) {
			ExecutionContext executionContext = new ExecutionContext();
			Assert.state(resource.exists(), "Resource does not exist: " + resource);
			executionContext.putString(KEY_NAME, resource.getFilename());
			executionContext.putString("opFileName", "output" + outputCounter++ + ".xml");
			map.put(PARTITION_KEY + partitionCounter, executionContext);
			partitionCounter++;
		}
		return map;
	}
}