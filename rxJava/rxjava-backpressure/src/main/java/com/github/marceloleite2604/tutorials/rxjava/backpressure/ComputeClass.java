package com.github.marceloleite2604.tutorials.rxjava.backpressure;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class ComputeClass {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputeClass.class);

	private String name;
	private long time;

	public ComputeClass(String name, long time) {
		super();
		this.name = name;
		this.time = time;
	}

	public void compute(Long value) {
		try {
			LOGGER.info("{}: Computing {}.", name, value);
			Thread.sleep(time);
		} catch (InterruptedException exception) {
			LOGGER.warn("Thread interrupted while computing value {}", value);
			Thread.currentThread()
					.interrupt();
		}
	}

	public void compute(List<Long> values) {
		LOGGER.info("Computing list {}.", values);
		values.stream()
				.forEach(this::compute);
	}

	public void compute(Observable<Long> observable) {
		LOGGER.info("Computing observable.");
		observable.subscribe(this::compute);
	}

}
