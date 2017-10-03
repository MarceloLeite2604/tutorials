package com.journaldev.design.structural.decorator;

/**
 * This class implements the basic of the Component Interface ({@link Car}). It
 * is called the Component Implementation.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1540/decorator-design-pattern-in-java-example">JournalDev
 *      Decorator Design Pattern</a>
 */
public class BasicCar implements Car {

	@Override
	public void assemble() {
		System.out.print("Basic Car.");
	}

}