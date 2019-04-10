package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.mapper;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextParaPropertiesMapper implements Mapper<ExecutionContext, Properties> {

	@Override
	public Properties map(ExecutionContext executionContext) {
		Properties properties = new Properties();
		for (Entry<String, Object> entry : executionContext.entrySet()) {
			properties.put(entry.getKey(), entry.getValue());
		}

		return properties;
	}

}
