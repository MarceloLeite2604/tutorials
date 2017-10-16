package com.journaldev.design.creational.abstractfactory;

/**
 * This is the factory for the {@link PC} class.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java">JournalDev
 *      Abstract Factory Design Pattern in Java.</a>
 *
 */
public class PCFactory implements ComputerAbstractFactory {

	private String ram;
	private String hdd;
	private String cpu;

	public PCFactory(String ram, String hdd, String cpu) {
		this.ram = ram;
		this.hdd = hdd;
		this.cpu = cpu;
	}

	@Override
	public Computer createComputer() {
		return new PC(ram, hdd, cpu);
	}

}