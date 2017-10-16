package com.journaldev.design.creational.singleton;

/**
 * <p>
 * Prior to Java 5, java memory model had a lot of issues and above approaches
 * used to fail in certain scenarios where too many threads try to get the
 * instance of the Singleton class simultaneously. So Bill Pugh came up with a
 * different approach to create the Singleton class using a inner static helper
 * class.
 * </p>
 * <p>
 * Notice the private inner static class {@link SingletonHelper} that contains
 * the instance of the singleton class. When the singleton class is loaded,
 * SingletonHelper class is not loaded into memory and only when someone calls
 * the getInstance method, this class gets loaded and creates the Singleton
 * class instance.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 *
 */
public class BillPughSingleton {

	private BillPughSingleton() {
	}

	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}

	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}