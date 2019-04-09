package com.github.marceloleite2604.tutorials.spring.job.configuration.mapper;

public interface Mapper<I, O> {

	public O map(I input);
}
