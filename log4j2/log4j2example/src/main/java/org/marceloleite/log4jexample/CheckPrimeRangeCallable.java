package org.marceloleite.log4jexample;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckPrimeRangeCallable implements Callable<Boolean> {

	Logger logger = LogManager.getLogger(CheckPrimeRangeCallable.class.getName());

	private static final int INTERRUPTION_CHECK_STEP = 100000;

	private int analizedNumber;

	private int startValue;

	private int endValue;

	public CheckPrimeRangeCallable(int analizedValue, int startValue, int endvalue) {
		super();
		logger.traceEntry("Parameters: {}, {}, {}", analizedValue, startValue, endvalue);
		this.analizedNumber = analizedValue;
		this.startValue = startValue;
		this.endValue = endvalue;
	}

	@Override
	public Boolean call() throws Exception {
		logger.traceEntry();
		int counter = startValue;
		boolean isPrime = true;
		boolean threadInterrupted = false;

		while (!threadInterrupted && isPrime && counter < endValue) {
			isPrime = (analizedNumber % counter != 0);
			counter++;

			if ((counter - startValue) % INTERRUPTION_CHECK_STEP == 0) {
				logger.trace("Checking interruption. Counter value: " + counter);
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
