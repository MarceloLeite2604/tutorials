package org.marceloleite.log4jexample;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckPrimeRangeCallable implements Callable<Boolean> {

	Logger logger = LogManager.getLogger(CheckPrimeRangeCallable.class.getName());

	private static final int INTERRUPTION_CHECK_STEP = 100000;

	private long analizedNumber;

	private long startValue;

	private long endValue;

	public CheckPrimeRangeCallable(long analizedValue, long startValue, long endvalue) {
		super();
		logger.traceEntry("Parameters: {}, {}, {}", analizedValue, startValue, endvalue);
		this.analizedNumber = analizedValue;
		this.startValue = startValue;
		this.endValue = endvalue;
	}

	@Override
	public Boolean call() throws Exception {
		logger.traceEntry();
		long counter = startValue;
		boolean isPrime = true;
		boolean threadInterrupted = false;

		while (!threadInterrupted && isPrime && counter < endValue) {
			isPrime = (analizedNumber % counter != 0);
			counter++;

			if ((counter - startValue) % INTERRUPTION_CHECK_STEP == 0) {
				threadInterrupted = Thread.interrupted();
			}
		}

		if (threadInterrupted) {
			logger.trace("Thread interrupted.");
			isPrime = false;
		}

		return logger.traceExit(isPrime);
	}

}
