package com.journaldev.design.creational.singleton.serialized;

import java.io.Serializable;

/**
 * <p>
 * Sometimes in distributed systems, we need to implement Serializable interface
 * in Singleton class so that we can store it’s state in file system and
 * retrieve it at later point of time.
 * </p>
 * <p>
 * The problem with serialized singleton class is that whenever we deserialize
 * it, it will create a new instance of the class. To overcome this scenario all
 * we need to do it provide the implementation of
 * {@link SerializedSingleton#readResolve()} method.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 */
public class SerializedSingleton implements Serializable {

	private static final long serialVersionUID = -7604766932017737115L;

	private SerializedSingleton() {
	}

	private static class SingletonHelper {
		private static final SerializedSingleton instance = new SerializedSingleton();
	}

	public static SerializedSingleton getInstance() {
		return SingletonHelper.instance;
	}

	/*
	 * The "readResolve" method is used for replacing the object read from the
	 * stream. When an object is read (deserialized), this function replaces it with
	 * the singleton instance.
	 */
	protected Object readResolve() {
		return getInstance();
	}

}