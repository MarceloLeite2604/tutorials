package org.marceloleite.tutorials.spring.job.configuration.mapper;

public interface Mapper<I, O> {

	public O map(I input);
}
