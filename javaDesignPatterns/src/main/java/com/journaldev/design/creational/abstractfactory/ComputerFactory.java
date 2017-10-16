package com.journaldev.design.creational.abstractfactory;

/**
 * This is the entry point class for the client to create the sub-classes.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java">JournalDev
 *      Abstract Factory Design Pattern in Java.</a>
 *
 */
public class ComputerFactory {

	public static Computer getComputer(ComputerAbstractFactory computerAbstractFactory) {
		return computerAbstractFactory.createComputer();
	}
}