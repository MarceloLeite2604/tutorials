package com.journaldev.design.creational.singleton;

/**
 * If the singleton class is not using a lot of resources, this is the
 * recommended approach. But in most of the scenarios, singleton classes are
 * created for resources such as file system, database connections, etc. and we
 * should avoid the instantiation until client calls the getInstance method.
 * Also the eager singleton doesn’t provide any options for exception handling.
 * To provide exception handling, the {@link StaticBlockSingleton} must be used.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 *
 */
public class EagerInitializedSingleton {

	private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

	// private constructor to avoid client applications to use constructor
	private EagerInitializedSingleton() {
	}

	public static EagerInitializedSingleton getInstance() {
		return instance;
	}
}