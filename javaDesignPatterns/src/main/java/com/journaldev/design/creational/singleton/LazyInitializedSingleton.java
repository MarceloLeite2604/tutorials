package com.journaldev.design.creational.singleton;

/**
 * The lazy initialized singleton declares a static method to implement to
 * create the instance only when it is requested. I works fine in case of single
 * threaded environment but when it comes to multithreaded systems, it can cause
 * issues if multiple threads are inside the if loop at the same time. It will
 * destroy the singleton pattern and both threads will get the different
 * instances of singleton class. To solve it, the {@link ThreadSafeSingleton}
 * and its variants must be used.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 *
 */
public class LazyInitializedSingleton {

	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton() {
	}

	public static LazyInitializedSingleton getInstance() {
		if (instance == null) {
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}
}