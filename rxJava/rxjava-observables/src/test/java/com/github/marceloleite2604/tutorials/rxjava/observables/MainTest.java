package com.github.marceloleite2604.tutorials.rxjava.observables;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.ConnectableObservable;
import rx.observables.GroupedObservable;
import rx.observables.SyncOnSubscribe;
import rx.subjects.PublishSubject;

public class MainTest {

	@Test
	public void testCreateObservable() {
		String result[] = { "" };

		Observable<String> observable = Observable.just("Hello");
		observable.subscribe(s -> result[0] = s);

		assertTrue(result[0].equals("Hello"));
	}

	@Test
	public void testCreateSubscriber() {
		String result[] = { "" };
		String[] letters = { "a", "b", "c", "d", "e", "f", "g" };
		Observable<String> observable = Observable.from(letters);

		Subscriber<String> subscriber = new Subscriber<String>() {

			@Override
			public void onCompleted() {
				result[0] += "_Completed";

			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onNext(String t) {
				result[0] += t;
			}

		};

		observable.subscribe(subscriber);
		assertTrue(result[0].equals("abcdefg_Completed"));
	}

	@Test
	public void testMap() {
		String[] letters = { "a", "b", "c", "d", "e", "f", "g" };
		String result[] = { "" };

		Observable.from(letters)
				.map(String::toUpperCase)
				.subscribe(letter -> result[0] += letter);
		assertTrue(result[0].equals("ABCDEFG"));
	}

	@Test
	public void testFlatMap() {
		String result[] = { "" };
		Observable.just("book1", "book2")
				.flatMap(s -> getTitle(s))
				.subscribe(l -> result[0] += l);

		assertTrue(result[0].equals("titlebook1titlebook2"));
	}

	private Observable<String> getTitle(String text) {
		return Observable.just("title" + text);
	}

	@Test
	public void testScan() {
		String[] letters = { "a", "b", "c" };
		String result[] = { "" };

		StringBuilder accumulatorStringBuilder = new StringBuilder();
		Func2<StringBuilder, String, StringBuilder> accumulatorMethod = StringBuilder::append;

		Observable.from(letters)
				.scan(accumulatorStringBuilder, accumulatorMethod)
				.subscribe(stringBuilder -> result[0] += stringBuilder.toString());
		assertTrue(result[0].equals("aababc"));
	}

	@Test
	public void testScan2() {
		Integer[] values = { 1, 3, 5, 7 };
		Integer result[] = { 0 };

		class IntegerAccumulator {

			private Integer value = 0;

			public IntegerAccumulator accumulate(Integer additionalValue) {
				value += additionalValue;
				return this;
			}

			public Integer getValue() {
				return value;
			}
		}

		IntegerAccumulator integerAccumulator = new IntegerAccumulator();
		Func2<IntegerAccumulator, Integer, IntegerAccumulator> accumulatorMethod = IntegerAccumulator::accumulate;

		Observable.from(values)
				.scan(integerAccumulator, accumulatorMethod)
				.subscribe(ia -> result[0] += ia.getValue());
		assertTrue(result[0].equals(30));
	}

	@Test
	public void testGroupBy() {
		Integer[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		String[] result = { "", "" };

		Func1<Integer, String> keySelector = this::oddOrEven;

		Observable<Integer> observableNumbers = Observable.from(numbers);

		Observable<GroupedObservable<String, Integer>> observableGroupedObservable = observableNumbers
				.groupBy(keySelector);

		Subscriber<GroupedObservable<String, Integer>> groupedObservableSubscriber = new Subscriber<GroupedObservable<String, Integer>>() {

			@Override
			public void onCompleted() {
				// Doesn't do anything.
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onNext(GroupedObservable<String, Integer> groupedObservable) {

				Subscriber<Integer> valueSubscriber = new Subscriber<Integer>() {

					@Override
					public void onCompleted() {
						// Doesn't do anything.
					}

					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
					}

					@Override
					public void onNext(Integer value) {
						if (groupedObservable.getKey()
								.toString()
								.equals("ODD")) {
							result[0] += value;
						} else {
							result[1] += value;
						}
					}

				};

				groupedObservable.subscribe(valueSubscriber);

			}
		};

		observableGroupedObservable.subscribe(groupedObservableSubscriber);
		assertTrue(result[0].equals("13579"));
		assertTrue(result[1].equals("02468"));
	}

	private String oddOrEven(Integer value) {
		if (value % 2 == 0) {
			return "EVEN";
		}

		return "ODD";
	}

	@Test
	public void testFilter() {
		Integer[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		String[] result = { "" };
		Observable.from(numbers)
				.filter(this::isOdd)
				.subscribe(value -> result[0] += value);

		assertTrue(result[0].equals("13579"));
	}

	private boolean isOdd(Integer value) {
		return value % 2 == 1;
	}

	@Test
	public void testDefaultIfEmpty() {

		String[] result = { "" };

		Observable<String> emptyObservable = Observable.<String>empty()
				.defaultIfEmpty("Observable is empty");

		emptyObservable.subscribe(string -> result[0] += string);

		assertTrue(result[0].equals("Observable is empty"));
	}

	@Test
	public void testDefaultIfEmpty2() {
		String[] letters = { "a", "b", "c" };
		String[] result = { "" };
		Observable<String> emptyObservable = Observable.from(letters)
				.defaultIfEmpty("Observable is empty");

		emptyObservable.subscribe(string -> result[0] += string);

		assertTrue(result[0].equals("abc"));
	}

	@Test
	public void takeWhile() {
		Integer[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Integer[] result = { 0 };

		Observable.from(numbers)
				.takeWhile(value -> value < 5)
				.subscribe(s -> result[0] += s);

		assertTrue(result[0] == 10);
	}

	@SuppressWarnings("squid:S2925")
	@Test
	public void connectableObservable() throws InterruptedException {
		String[] result = { "" };
		ConnectableObservable<Long> connectableObservable = Observable
				.interval(200, TimeUnit.MILLISECONDS)
				.publish();

		connectableObservable.subscribe(value -> result[0] += value);
		assertFalse(result[0].equals("01"));

		connectableObservable.connect();
		Thread.sleep(500);

		assertTrue(result[0].equals("01"));
	}

	@Test
	public void testSingle() {
		String[] result = { "" };
		Observable<String> observable = Observable.just("Hello");

		Single<String> single = observable.toSingle();

		single = single.doOnSuccess(string -> result[0] += string);

		single = single.doOnError(error -> {
			throw new RuntimeException(error.getMessage());
		});

		single.subscribe();

		assertTrue(result[0], result[0].equals("Hello"));
	}

	@Test
	public void testObservers() {
		Integer[] result = { 0, 0 };

		PublishSubject<Integer> subject = PublishSubject.create();

		Observer<Integer> firstObserver = createObserver(result, 0, "First observer completed.");
		Observer<Integer> secondObserver = createObserver(result, 1, "Second observer completed.");

		subject.subscribe(firstObserver);
		subject.onNext(1);
		subject.onNext(2);
		subject.onNext(3);
		subject.subscribe(secondObserver);
		subject.onNext(4);
		subject.onCompleted();

		assertTrue(result[0] + result[1] == 14);
	}

	private Observer<Integer> createObserver(Integer[] result, int resultIndex, String message) {
		return new Observer<Integer>() {
			@Override
			public void onNext(Integer value) {
				result[resultIndex] += value;
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("error");
			}

			@Override
			public void onCompleted() {
				System.out.println(message);
			}
		};
	}

	@Test
	public void testUsing() {
		String[] result = { "", "" };

		Func0<String> resourceFactory = () -> "MyResource";

		Func1<String, Observable<Character>> observableFactory = new Func1<String, Observable<Character>>() {

			@Override
			public Observable<Character> call(String string) {

				Func0<Iterator<Character>> generator = () -> string.chars()
						.mapToObj(value -> (char) value)
						.collect(Collectors.toList())
						.iterator();

				Func2<Iterator<Character>, Observer<? super Character>, Iterator<Character>> onNext = (
						iterator, observer) -> {
					if (iterator.hasNext()) {
						observer.onNext(iterator.next());
					} else {
						observer.onCompleted();
					}
					return iterator;
				};

				SyncOnSubscribe<Iterator<Character>, Character> syncOnSubscribe = SyncOnSubscribe
						.<Iterator<Character>, Character>createStateful(generator, onNext);

				return Observable.create(syncOnSubscribe);
			}
		};

		Action1<String> disposeAction = new Action1<String>() {

			@Override
			public void call(String string) {
				System.out.println("Disposed: " + string);
			}
		};

		Observable<Character> observable = Observable.using(resourceFactory, observableFactory,
				disposeAction);

		observable.subscribe(character -> result[0] += character,
				character -> result[1] += character);
		assertTrue(result[0].equals("MyResource"));
	}

	@Test
	public void testUsing2() throws InterruptedException {
		String[] result = { "" };

		String expectedResult = "ThisIsMyInputLine";

		Func0<BufferedReader> resourceFactory = this::createBufferedReader;

		Func1<BufferedReader, Observable<String>> observableFactory = new Func1<BufferedReader, Observable<String>>() {

			@Override
			public Observable<String> call(BufferedReader bufferedReader) {
				SyncOnSubscribe<Void, String> onSubscribe = SyncOnSubscribe
						.<String>createStateless((observer) -> {
							try {
								String line = bufferedReader.readLine();

								if (line != null) {
									observer.onNext(line);
								} else {
									observer.onCompleted();
								}

							} catch (IOException exception) {
								observer.onError(exception);
							}
						});
				return Observable.create(onSubscribe);
			}
		};

		Action1<BufferedReader> disposeAction = this::disposeBufferedReader;

		Observable<String> observable = Observable.using(resourceFactory, observableFactory,
				disposeAction);

		observable.subscribe(string -> result[0] += string, throwable -> {
			throw new RuntimeException(throwable);
		});

		assertTrue(String.format("result[0] should be \"%s\".", expectedResult),
				result[0].equals("ThisIsMyInputLine"));
	}

	private BufferedReader createBufferedReader() {
		try {
			System.out.println("Opening buffered reader.");
			return Files.newBufferedReader(Paths.get("src/test/resources/testUsing-input.txt"));
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private void disposeBufferedReader(BufferedReader bufferedReader) {
		try {
			System.out.println("Closing buffered reader.");
			bufferedReader.close();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
