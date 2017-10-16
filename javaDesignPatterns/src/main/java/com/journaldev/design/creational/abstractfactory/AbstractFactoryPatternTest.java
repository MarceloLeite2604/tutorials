package com.journaldev.design.creational.abstractfactory;

/**
 * <p>
 * Advantages of abstract factory design pattern are:
 * <ul>
 * <li>It provides approach to code for interface rather than
 * implementation.</li>
 * <li>It is a “factory of factories” and can be easily extended to accommodate
 * more products, for example we can add another sub-class Laptop and a factory
 * LaptopFactory.</li>
 * <li>It is robust and avoid conditional logic of Factory pattern.</li>
 * </ul>
 * </p>
 * <p>
 * In Abstract Factory pattern, we get rid of "if-else" block necessary on
 * normal factory pattern.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java">JournalDev
 *      Abstract Factory Design Pattern in Java.</a>
 *
 */
public class AbstractFactoryPatternTest {

	public static void main(String[] args) {
		testAbstractFactory();
	}

	private static void testAbstractFactory() {
		Computer pc = ComputerFactory.getComputer(new PCFactory("2 GB", "500 GB", "2.4 GHz"));
		Computer server = ComputerFactory.getComputer(new ServerFactory("16 GB", "1 TB", "2.9 GHz"));
		System.out.println("AbstractFactory PC Config::" + pc);
		System.out.println("AbstractFactory Server Config::" + server);
	}
}