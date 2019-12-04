package com.github.marceloleite2604.tutorials.grpc.server;

import br.com.github.marceloleite2604.tutorials.grpc.primenumbers.Boolean;
import br.com.github.marceloleite2604.tutorials.grpc.primenumbers.Number;
import br.com.github.marceloleite2604.tutorials.grpc.primenumbers.PrimeNumbersGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeNumbersServer extends PrimeNumbersGrpc.PrimeNumbersImplBase {

	private static final Logger LOGGER = Logger.getLogger(PrimeNumbersServer.class.getName());
	
	static {
		LOGGER.setLevel(Level.INFO);
	}

	private final Server server;

	public PrimeNumbersServer(int port, NumberUtil numberUtil) {
		this(ServerBuilder.forPort(port), numberUtil);
	}

	private PrimeNumbersServer(ServerBuilder<?> serverBuilder, NumberUtil numberUtil) {
		server = serverBuilder.addService(new PrimeNumbersService(numberUtil))
				.build();
	}

	public void start() throws IOException {
		server.start();
		LOGGER.info("Prime numbers server is running.");
		Runtime.getRuntime()
				.addShutdownHook(new Thread() {
					@Override
					public void run() {
						System.err.println("JVM is shutting down. Stopping prime numbers server.");
						PrimeNumbersServer.this.stop();
					}
				});
	}

	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	public void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	private static class PrimeNumbersService extends PrimeNumbersGrpc.PrimeNumbersImplBase {

		private static final String TEMPLATE_MESSAGE_SKIPPING_PRIME_VERIFICATION = "Skipping verification for number %d.";

		private static final String TEMPLATE_MESSAGE_CHECKING_VALUE_IS_PRIME = "Checking if %d is prime.";

		private NumberUtil numberUtil;

		PrimeNumbersService(NumberUtil numberUtil) {
			this.numberUtil = numberUtil;
		}

		@Override
		public void isPrime(Number request, StreamObserver<Boolean> responseObserver) {
			boolean prime = numberUtil.isPrime(request.getValue());

			Boolean response = Boolean.newBuilder()
					.setValue(prime)
					.build();

			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}

		@Override
		public void allPrimesUntil(Number request, StreamObserver<Number> responseObserver) {
			int upperBound = request.getValue();

			for (int counter = 2; counter < upperBound; counter++) {
				if (numberUtil.isPrime(counter)) {
					responseObserver.onNext(Number.newBuilder()
							.setValue(counter)
							.build());
				}
			}
			responseObserver.onCompleted();
		}

		@Override
		public StreamObserver<Number> areAllPrimes(StreamObserver<Boolean> responseObserver) {
			return new StreamObserver<Number>() {
				boolean result = true;

				@Override
				public void onNext(Number number) {
					int value = number.getValue();
					if (result) {
						logInfo(TEMPLATE_MESSAGE_CHECKING_VALUE_IS_PRIME, value);
						result = numberUtil.isPrime(value);
					} else {
						logInfo(TEMPLATE_MESSAGE_SKIPPING_PRIME_VERIFICATION, value);
					}
				}

				@Override
				public void onError(Throwable throwable) {
					LOGGER.log(Level.SEVERE, throwable,
							() -> "An error occurred while receiving gRPC parameters.");

				}

				@Override
				public void onCompleted() {
					responseObserver.onNext(Boolean.newBuilder()
							.setValue(result)
							.build());
					responseObserver.onCompleted();
				}
			};
		}

		@Override
		public StreamObserver<Number> whichArePrimes(StreamObserver<Boolean> responseObserver) {
			return new StreamObserver<Number>() {

				@Override
				public void onNext(Number number) {
					int value = number.getValue();
					logInfo(TEMPLATE_MESSAGE_CHECKING_VALUE_IS_PRIME, value);
					boolean prime = numberUtil.isPrime(value);
					responseObserver.onNext(Boolean.newBuilder()
							.setValue(prime)
							.build());
				}

				@Override
				public void onError(Throwable throwable) {
					LOGGER.log(Level.SEVERE, throwable,
							() -> "An error occurred while receiving gRPC parameters.");

				}

				@Override
				public void onCompleted() {
					responseObserver.onCompleted();
				}
			};
		}

		private void logInfo(String messageTemplate, Object... parameters) {
			if (Level.INFO.equals(LOGGER.getLevel())) {
				LOGGER.info(String.format(messageTemplate, parameters));
			}
		}
	}
}
