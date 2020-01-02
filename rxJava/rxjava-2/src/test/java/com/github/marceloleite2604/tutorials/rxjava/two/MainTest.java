package com.github.marceloleite2604.tutorials.rxjava.two;

import static org.assertj.core.api.Assertions.assertThat;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("squid:S2925")
public class MainTest {

	private final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

	@Before
	public void setUp() {
		LOGGER.info("Test unit started.");
	}

	@After
	public void tearDown() {
		LOGGER.info("Test unit completed.");
		LOGGER.info("--------------------");
	}

	// @Test
	// public void testObservable() throws InterruptedException {
	// LOGGER.info("testObservable()");
	// DisposableObserver<String> disposableObserver = new
	// DisposableObserver<String>() {
	//
	// private final Logger LOGGER =
	// LoggerFactory.getLogger(DisposableObserver.class);
	//
	// @Override
	// protected void onStart() {
	// LOGGER.info("Started.");
	// }
	//
	// @Override
	// public void onNext(String string) {
	// LOGGER.info("Next value is: \"{}\"", string);
	// }
	//
	// @Override
	// public void onError(Throwable throwable) {
	// LOGGER.error("An error occurred.", throwable);
	// }
	//
	// @Override
	// public void onComplete() {
	// LOGGER.info("Completed.");
	// }
	// };
	//
	// Disposable disposable = Observable.just("Hello, world!")
	// .delay(100, TimeUnit.MILLISECONDS)
	// .subscribeWith(disposableObserver);
	//
	// Thread.sleep(150);
	//
	// disposable.dispose();
	//
	// assertThat(disposable.isDisposed()).isTrue();
	// }

	// @Test
	// public void testObservable2() throws InterruptedException {
	// LOGGER.info("testObservable2()");
	// DisposableObserver<Long> disposableObserver = new DisposableObserver<Long>()
	// {
	//
	// private final Logger LOGGER =
	// LoggerFactory.getLogger(DisposableObserver.class);
	//
	// @Override
	// protected void onStart() {
	// LOGGER.info("Started.");
	// }
	//
	// @Override
	// public void onNext(Long value) {
	// if (value % 50 == 0) {
	// LOGGER.info("Received value: \"{}\"", value);
	// }
	// }
	//
	// @Override
	// public void onError(Throwable throwable) {
	// LOGGER.error("An error occurred.", throwable);
	// }
	//
	// @Override
	// public void onComplete() {
	// LOGGER.info("Completed.");
	// }
	// };
	//
	// Disposable disposable = Observable.rangeLong(1L, 200L)
	// .subscribeWith(disposableObserver);
	//
	// Thread.sleep(150);
	//
	// disposable.dispose();
	//
	// assertThat(disposable.isDisposed()).isTrue();
	// }

	@Test
	public void testFlowable() throws InterruptedException {
		LOGGER.info("testFlowable()");
		/*
		 * 
		 * The main differences between a subscriber and a observer isthat subscribers
		 * are part of the Reactive Stream specification. Therefore, they must signal
		 * demand via "Subscription.request(long n)" method to receive "onNext" signals.
		 * Since observables do not support backpressure, there is no need to implement
		 * a "request" method on observers.
		 * 
		 * Source:
		 * https://github.com/ReactiveX/RxJava/issues/4515#issuecomment-283414763
		 * 
		 */
		DisposableSubscriber<String> disposableObserver = new DisposableSubscriber<String>() {

			private final Logger LOGGER = LoggerFactory.getLogger(DisposableSubscriber.class);

			@Override
			protected void onStart() {
				LOGGER.info("Started.");
				request(1L);
			}

			@Override
			public void onNext(String string) {
				LOGGER.info("Next value is: \"{}\"", string);
				request(1L);
			}

			@Override
			public void onError(Throwable throwable) {
				LOGGER.error("An error occurred.", throwable);
			}

			@Override
			public void onComplete() {
				LOGGER.info("Completed.");
			}
		};

		Disposable disposable = Flowable.just("Hello, world!")
				.subscribeWith(disposableObserver);

		Thread.sleep(150);

		disposable.dispose();

		assertThat(disposable.isDisposed()).isTrue();
	}

	@Test
	public void testFlowable2() throws InterruptedException {
		LOGGER.info("testFlowable2()");
		DisposableSubscriber<Long> disposableObserver = new DisposableSubscriber<Long>() {

			private final Logger LOGGER = LoggerFactory.getLogger(DisposableSubscriber.class);

			@Override
			protected void onStart() {
				LOGGER.info("Started.");
				request(1L);
			}

			@Override
			public void onNext(Long value) {
				if (value % 100_000L == 0) {
					LOGGER.info("Received value: \"{}\"", value);
				}
				request(1L);
			}

			@Override
			public void onError(Throwable throwable) {
				LOGGER.error("An error occurred.", throwable);
			}

			@Override
			public void onComplete() {
				LOGGER.info("Completed.");
			}
		};

		Disposable disposable = Flowable.rangeLong(1L, 1_000_000L)
				.subscribeWith(disposableObserver);

		Thread.sleep(150);

		disposable.dispose();

		assertThat(disposable.isDisposed()).isTrue();
	}

	@Test
	public void testCreateFlowable() throws InterruptedException {

		String[] result = { "" };
		String doneMessage = "Done";

		LOGGER.info("testCreateFlowable()");

		FlowableOnSubscribe<String> flowableOnSubscribe = new FlowableOnSubscribe<String>() {

			@Override
			public void subscribe(FlowableEmitter<String> emitter) throws Exception {
				// Signal an item.
				emitter.onNext("Hello");

				// Could be some blocking operation.
				Thread.sleep(100);

				// The consumer might have cancelled the flow.
				if (emitter.isCancelled()) {
					return;
				}

				emitter.onNext("World!");

				Thread.sleep(100);

				// The end-of-sequence has to be signaled, otherwise the consumers may never
				// finish.
				emitter.onComplete();

			}
		};

		Flowable<String> flowable = Flowable.create(flowableOnSubscribe,
				BackpressureStrategy.BUFFER);

		LOGGER.info("Subscribing.");

		/*
		 * Subscription will be done on the main thread (blocking).
		 */
		flowable.subscribe(LOGGER::info,
				(throwable) -> LOGGER.error("An error occurred.", throwable),
				() -> result[0] = "Done");

		Thread.sleep(300);

		LOGGER.info("Done.");

		assertThat(result[0]).isEqualTo(doneMessage);
	}

	@Test
	public void testCreateFlowableDifferentScheduler() throws InterruptedException {

		String[] result = { "" };
		String doneMessage = "Done";

		LOGGER.info("testCreateFlowableDifferentScheduler()");

		FlowableOnSubscribe<Long> flowableOnSubscribe = new FlowableOnSubscribe<Long>() {

			@Override
			public void subscribe(FlowableEmitter<Long> emitter) throws Exception {

				for (long counter = 1L; counter < 1_000_000L; counter++) {
					emitter.onNext(counter);

					if (emitter.isCancelled()) {
						return;
					}
				}

				emitter.onComplete();

			}
		};

		Flowable<Long> flowable = Flowable.create(flowableOnSubscribe, BackpressureStrategy.BUFFER);

		LOGGER.info("Subscribing.");

		Consumer<Long> onNext = (value) -> {
			if (value % 100_000L == 0) {
				LOGGER.info("{}", value);
			}
		};

		// Subscription will be done on a different schedule, so "main" thread can
		// continue its execution.
		flowable.subscribeOn(Schedulers.computation())
				.subscribe(onNext, (throwable) -> LOGGER.error("An error occurred.", throwable),
						() -> result[0] = "Done");

		// Since main thread is free, we'll have to wait the flow to complete.
		Thread.sleep(300);

		LOGGER.info("Done.");

		assertThat(result[0]).isEqualTo(doneMessage);
	}
}
