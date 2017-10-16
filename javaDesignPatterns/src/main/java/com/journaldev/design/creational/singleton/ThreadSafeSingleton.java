package com.journaldev.design.creational.singleton;

/**
 * The thread safe singleton works fine and provides thread-safety, but it
 * reduces the performance because of cost associated with the synchronized
 * method. To surpass this problem, the
 * {@link ThreadSafeSingleton#getInstanceUsingDoubleLocking()} method is used.
 * It uses double check locking principle. In this approach, the synchronized
 * block is used inside the if condition with an additional check to ensure that
 * only one instance of singleton class is created.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 *
 */
public class ThreadSafeSingleton {

	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
	}

	public static synchronized ThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}

	public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
		if (instance == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (instance == null) {
					instance = new ThreadSafeSingleton();
				}
			}
		}
		return instance;
	}

}