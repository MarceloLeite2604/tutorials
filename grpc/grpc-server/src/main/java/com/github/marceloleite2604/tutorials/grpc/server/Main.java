package com.github.marceloleite2604.tutorials.grpc.server;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		PrimeNumbersServer primeNumbersServer = new PrimeNumbersServer(8091, new NumberUtil());
		primeNumbersServer.start();
		primeNumbersServer.blockUntilShutdown();
	}
}
