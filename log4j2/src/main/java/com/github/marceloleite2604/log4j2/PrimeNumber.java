package com.github.marceloleite2604.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeNumber {

	private static Logger logger = LogManager.getLogger(PrimeNumber.class.getName());

	private static int NUMBER_OF_THREADS = 4;

	public static void main(String[] args) {
		logger.traceEntry();
		long numberToVerify = 2038074743;

		logger.info("Checking if " + numberToVerify + " is a prime number.");
		long startTime = System.currentTimeMillis();
		CheckPrime checkPrime = new CheckPrime(NUMBER_OF_THREADS);
		boolean isPrime = checkPrime.isNumberPrime(numberToVerify);
		long stopTime = System.currentTimeMillis();
		if (isPrime) {
			System.out.println("The number " + numberToVerify + " is prime.");
		} else {
			System.out.println("The number " + numberToVerify + " is NOT prime.");
		}

		System.out.println("Total execution time: " + (stopTime - startTime) + " ms.");

		logger.traceExit();
	}
}
