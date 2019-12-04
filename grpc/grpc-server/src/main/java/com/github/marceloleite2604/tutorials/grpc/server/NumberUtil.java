package com.github.marceloleite2604.tutorials.grpc.server;

public class NumberUtil {

	public boolean isPrime(int number) {
		for (int counter = 2; counter < number; counter++) {
			if (number % counter == 0) {
				return false;
			}
		}
		return true;
	}
}
