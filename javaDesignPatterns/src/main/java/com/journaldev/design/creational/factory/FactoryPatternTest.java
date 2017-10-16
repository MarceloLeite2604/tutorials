package com.journaldev.design.creational.factory;

/**
 * <p>
 * Advantages of Factory Design Pattern are:
 * <ul>
 * <li>Provides approach to code for interface rather than implementation.</li>
 * <li>Removes the instantiation of actual implementation classes from client
 * code.</li>
 * <li>Makes our code more robust, less coupled and easy to extend. For example,
 * we can easily change {@link PC} implementation because client program is
 * unaware of this.</li>
 * <li>Provides abstraction between implementation and client classes through
 * inheritance.</li>
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1392/factory-design-pattern-in-java">JournalDev
 *      Factory Design Pattern in Java.</a>
 *
 */
public class FactoryPatternTest {

	public static void main(String[] args) {
		Computer pc = ComputerFactory.getComputer("pc", "2 GB", "500 GB", "2.4 GHz");
		Computer server = ComputerFactory.getComputer("server", "16 GB", "1 TB", "2.9 GHz");
		System.out.println("Factory PC Config::" + pc);
		System.out.println("Factory Server Config::" + server);
	}

}