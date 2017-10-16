package com.journaldev.design.creational.factory;

/**
 * <p>
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
public class ComputerFactory {

	public static Computer getComputer(String type, String ram, String hdd, String cpu) {
		if ("PC".equalsIgnoreCase(type))
			return new PC(ram, hdd, cpu);
		else if ("Server".equalsIgnoreCase(type))
			return new Server(ram, hdd, cpu);

		return null;
	}
}