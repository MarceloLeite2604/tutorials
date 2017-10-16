package com.journaldev.design.creational.factory;

/**
 * <p>
 * This is the factory class. It is responsible to analyze the creation request,
 * instantiate the correct object with the correct properties and return it to
 * the client.
 * </p>
 * <p>
 * It is possible to keep the factory class as a singleton or its
 * instance-returner method as static.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1392/factory-design-pattern-in-java">JournalDev
 *      Factory Design Pattern in Java.</a>
 *
 */
public class ComputerFactory {

	public static Computer getComputer(String type, String ram, String hdd, String cpu) {
		if ("PC".equalsIgnoreCase(type))
			return new PC(ram, hdd, cpu);
		else if ("Server".equalsIgnoreCase(type))
			return new Server(ram, hdd, cpu);

		return null;
	}
}