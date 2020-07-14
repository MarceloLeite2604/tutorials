package com.github.marceloleite2604.tutorials.rxjava.backpressure;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public Observable<Long> createObservable() {
		LOGGER.info("Creating observable.");
		return Observable.interval(1, TimeUnit.MILLISECONDS)
				.takeWhile(value -> value < 600);
	}

	public PublishSubject<Long> createPublishSubject() {
		return PublishSubject.<Long>create();
	}

	public void addValues(PublishSubject<Long> publishSubject) {
		createObservable().subscribe(value -> addValueOnPublishSubject(value, publishSubject));
	}

	public void addValues(PublishSubject<Long> publishSubject, Observable<Long> observable) {
		observable.subscribe(value -> addValueOnPublishSubject(value, publishSubject));
		
	}

	private void addValueOnPublishSubject(Long value, PublishSubject<Long> publishSubject) {
		LOGGER.info("Adding {} on publishSubject.", value);
		publishSubject.onNext(value);
	}

	public void unsubscribe(Subscription subscription) {
		LOGGER.info("Unsubscribing.");
		subscription.unsubscribe();
	}
}
