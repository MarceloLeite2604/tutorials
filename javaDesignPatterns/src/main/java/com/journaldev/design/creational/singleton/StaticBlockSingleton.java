package com.journaldev.design.creational.singleton;

/**
 * Static block initialization implementation is similar to
 * {@link EagerInitializedSingleton}, except that instance of class is created
 * in the static block that provides option for exception handling.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 *
 */
public class StaticBlockSingleton {

	private static StaticBlockSingleton instance;

	private StaticBlockSingleton() {
	}

	// static block initialization for exception handling
	static {
		try {
			instance = new StaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
	}

	public static StaticBlockSingleton getInstance() {
		return instance;
	}
}