package com.journaldev.design.creational.abstractfactory;

/**
 * This interface defines the contract for the abstract factories, which will be
 * responsible to instantiate the requested objects.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java">JournalDev
 *      Abstract Factory Design Pattern in Java.</a>
 *
 */
public interface ComputerAbstractFactory {

	public Computer createComputer();

}