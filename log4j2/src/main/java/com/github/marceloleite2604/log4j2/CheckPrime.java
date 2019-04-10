package com.github.marceloleite2604.log4j2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckPrime {

	Logger logger = LogManager.getLogger(CheckPrime.class.getName());

	private int numberOfThreads;

	public CheckPrime(int numberOfThreads) {
		super();
		logger.traceEntry("Number of threads: {}", numberOfThreads);
		this.numberOfThreads = numberOfThreads;
	}

	public boolean isNumberPrime(long number) {
		logger.traceEntry("Number: {}", number);

		boolean isPrime = false;
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		Set<Future<Boolean>> futureSet = new HashSet<Future<Boolean>>();

		long startValue = 3;
		long stepDivision = ((number - startValue) / numberOfThreads);
		long endValue = startValue + stepDivision;
		logger.debug("Step division: {}", stepDivision);

		for (int counter = 0; counter < numberOfThreads; counter++) {
			CheckPrimeRangeCallable checkPrimeRangeCallable = new CheckPrimeRangeCallable(number, startValue, endValue);
			futureSet.add(executorService.submit(checkPrimeRangeCallable));
			startValue = endValue + 1;
			endValue = ((startValue + stepDivision) >= number ? (number - 1) : startValue + stepDivision);
		}

		for (Future<Boolean> future : futureSet) {
			try {
				isPrime = future.get();
				if (!isPrime) {
					break;
				}
			} catch (InterruptedException | ExecutionException e) {
				logger.info("Thread interrupted.");
				e.printStackTrace();
			}
		}

		executorService.shutdownNow();

		return logger.traceExit(isPrime);
	}
}
