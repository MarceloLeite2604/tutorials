package org.marceloleite.log4jexample;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeNumber {

	private static Logger logger = LogManager.getLogger(PrimeNumber.class.getName());

	public static void main(String[] args) {
		logger.traceEntry();
		//int numberToVerify = (int) (new Random().nextDouble() * Integer.MAX_VALUE);
		int numberToVerify = 1011;

		logger.info("Checking if " + numberToVerify + " is a prime number.");
		CheckPrime checkPrime = new CheckPrime(3);
		if (checkPrime.isNumberPrime(numberToVerify)) {
			logger.info("The number " + numberToVerify + " is prime.");
		}

		logger.traceExit();
	}

	/*private static boolean isPrimeNumber(int number) {
		logger.traceEntry("Paramenters: {}", number);

		boolean result = true;

		if (number % 2 == 0) {
			return false;
		}

		int counter = 3;
		while (result == true && counter < number) {
			result = (number % counter == 0);
			counter++;
		}

		return logger.traceExit(result);
	}*/

}
