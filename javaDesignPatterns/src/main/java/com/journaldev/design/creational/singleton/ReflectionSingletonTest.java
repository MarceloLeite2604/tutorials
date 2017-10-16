package com.journaldev.design.creational.singleton;

import java.lang.reflect.Constructor;

/**
 * Reflection can be used to destroy all the singleton implementation
 * approaches.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples">JournalDev
 *      Singleton Design Pattern Best Practices with examples.</a>
 *
 */
public class ReflectionSingletonTest {

	public static void main(String[] args) {
		EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton instanceTwo = null;
		try {
			Constructor<?>[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
			for (Constructor<?> constructor : constructors) {
				/*
				 * The code below will destroy the singleton pattern, as is changes the
				 * constructor accessibility to public.
				 */
				constructor.setAccessible(true);
				instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* The hash output proves that there is two instances of the singleton class. */
		System.out.println(instanceOne.hashCode());
		System.out.println(instanceTwo.hashCode());
	}

}