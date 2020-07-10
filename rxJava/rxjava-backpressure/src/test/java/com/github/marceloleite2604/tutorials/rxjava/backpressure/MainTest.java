package com.github.marceloleite2604.tutorials.rxjava.backpressure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@SuppressWarnings("squid:S2925")
public class MainTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

	private Main main;

	@Before
	public void setUp() {
		LOGGER.info("Setting up test.");
		main = new Main();
	}

	/*
	 * Cold observables repeat their values for each subscriber.
	 */
	// @Test
	// public void testColdObservable() throws InterruptedException {
	//
	// ComputeClass computeClassOne = new ComputeClass("CPU 1", 2L);
	// ComputeClass computeClassTwo = new ComputeClass("CPU 2", 2L);
	//
	// Observable<Long> observable = Observable.interval(1, TimeUnit.MILLISECONDS)
	// .takeWhile(value -> value < 10);
	//
	// List<Subscription> subscriptions = new ArrayList<>();
	// subscriptions.add(observable.subscribe(computeClassOne::compute));
	//
	// Thread.sleep(25);
	//
	// subscriptions.add(observable.subscribe(computeClassTwo::compute));
	//
	// Thread.sleep(25);
	//
	// subscriptions.forEach(Subscription::unsubscribe);
	//
	// assertTrue(true);
	// }

	/*
	 * Hot observables discard items after they have been used by any subscriber.
	 */
	// @Test
	// public void testHotObservable() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// ComputeClass computeClassOne = new ComputeClass("CPU 1", 100L);
	// ComputeClass computeClassTwo = new ComputeClass("CPU 2", 2L);
	//
	// Observable<Long> observable = Observable.interval(1, TimeUnit.MILLISECONDS)
	// .takeWhile(value -> value < 10);
	//
	// ConnectableObservable<Long> connectableObservable = observable.publish();
	// Observable<Long> autoConnectObservable = connectableObservable.autoConnect();
	//
	// List<Subscription> subscriptions = new ArrayList<>();
	// subscriptions.add(autoConnectObservable.subscribe(computeClassOne::compute));
	//
	// Thread.sleep(10);
	//
	// subscriptions.add(autoConnectObservable.subscribe(computeClassTwo::compute));
	//
	// Thread.sleep(500);
	//
	// subscriptions.forEach(Subscription::unsubscribe);
	//
	// assertThat(error[0]).isInstanceOf(MissingBackpressureException.class);
	// }

	@Test
	public void testBackpressure() throws InterruptedException {
		ComputeClass computeClassOne = new ComputeClass("CPU 1", 1000L);

		PublishSubject<Long> publishSubject = PublishSubject.create();

		Observable<Long> observable = publishSubject.observeOn(Schedulers.computation());
		
		ConnectableObservable<Long> connectableObservable = observable.publish();
		
		Observable<Long> observable2 = connectableObservable.onBackpressureBuffer(10L, () -> System.out.println("Backpressure buffer!"));
		
		
		observable2.subscribe(computeClassOne::compute,
				(throwable) -> LOGGER.info("Something went wrong", throwable));

		for (long counter = 0L; counter < 512L; counter++) {
			System.out.println(counter);
			publishSubject.onNext(counter);
		}

		Thread.sleep(1000);

	}

	// @Test
	// public void testHotObservable() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// PublishSubject<Long> publishSubject = PublishSubject.<Long>create();
	//
	// Observable<Long> observable =
	// publishSubject.observeOn(Schedulers.computation());
	//
	// Subscription subscription = observable.subscribe(main::compute, (throwable)
	// -> {
	// LOGGER.error("Error", throwable);
	// error[0] = throwable;
	// });
	//
	// main.addValues(publishSubject);
	//
	// Thread.sleep(200);
	//
	// main.unsubscribe(subscription);
	//
	// assertThat(error[0]).isInstanceOf(MissingBackpressureException.class);
	// }

	// @Test
	// public void testBufferingHotObservable() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// PublishSubject<Long> publishSubject = main.createPublishSubject();
	//
	// Observable<List<Long>> observable = publishSubject.buffer(3)
	// .observeOn(Schedulers.computation());
	//
	// Subscription subscription = observable.subscribe(main::compute, (throwable)
	// -> {
	// LOGGER.error("Error", throwable);
	// error[0] = throwable;
	// });
	//
	// main.addValues(publishSubject);
	//
	// Thread.sleep(400);
	//
	// main.unsubscribe(subscription);
	//
	// assertThat(error[0]).isInstanceOf(MissingBackpressureException.class);
	// }

	// @Test
	// public void testWindowingHotObservable() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// PublishSubject<Long> publishSubject = main.createPublishSubject();
	//
	// Observable<Observable<Long>> observable = publishSubject.window(3)
	// .observeOn(Schedulers.computation());
	//
	// Subscription subscription = observable.subscribe(main::compute, (throwable)
	// -> {
	// LOGGER.error("Error", throwable);
	// error[0] = throwable;
	// });
	//
	// main.addValues(publishSubject);
	//
	// Thread.sleep(400);
	//
	// main.unsubscribe(subscription);
	//
	// assertThat(error[0]).isInstanceOf(MissingBackpressureException.class);
	// }

	// @Test
	// public void testSampleHotObservable() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// PublishSubject<Long> publishSubject = main.createPublishSubject();
	//
	// Observable<Long> observable = publishSubject.sample(2, TimeUnit.MILLISECONDS)
	// .observeOn(Schedulers.computation());
	//
	// Subscription subscription = observable.subscribe(main::compute, (throwable)
	// -> {
	// LOGGER.error("Error", throwable);
	// error[0] = throwable;
	// });
	//
	// main.addValues(publishSubject);
	//
	// Thread.sleep(500);
	//
	// main.unsubscribe(subscription);
	//
	// assertThat(error[0]).isInstanceOf(MissingBackpressureException.class);
	// }

	// @Test
	// public void testThrottleFirstHotObservable() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// PublishSubject<Long> publishSubject = main.createPublishSubject();
	//
	// Observable<Long> observable = publishSubject.throttleFirst(2,
	// TimeUnit.MILLISECONDS)
	// .observeOn(Schedulers.computation());
	//
	// Subscription subscription = observable.subscribe(main::compute, (throwable)
	// -> {
	// LOGGER.error("Error", throwable);
	// error[0] = throwable;
	// });
	//
	// main.addValues(publishSubject);
	//
	// Thread.sleep(600);
	//
	// main.unsubscribe(subscription);
	//
	// assertThat(error[0]).isInstanceOf(MissingBackpressureException.class);
	// }

	// @Test
	// public void testBackpressureBuffer() throws InterruptedException {
	// Throwable[] error = { null };
	//
	// Observable<Long> observable = Observable.range(1, 500)
	// .map((value) -> value.longValue());
	//
	// PublishSubject<Long> publishSubject = PublishSubject.<Long>create();
	//
	// Observable<Long> observable2 =
	// publishSubject.observeOn(Schedulers.computation(), 2);
	//
	// main.addValues(publishSubject, observable);
	//
	// //Observable<Long> observable3 = observable2.onBackpressureBuffer(2, () ->
	// System.out.println("Overflow"));
	//
	// // Observable<Long> observable2 =
	// publishSubject.observeOn(Schedulers.computation(), 2);
	//
	// Subscription subscription = publishSubject.subscribe(main::compute,
	// (throwable)
	// -> {
	// LOGGER.error("Error", throwable);
	// error[0] = throwable;
	// });
	//
	// Thread.sleep(3000);
	//
	// main.unsubscribe(subscription);
	//
	// assertThat(error[0]).isNull();
	// }

}
