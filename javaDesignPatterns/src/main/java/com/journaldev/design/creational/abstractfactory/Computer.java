package com.journaldev.design.creational.abstractfactory;

/**
 * This is the abstract super class of the design pattern. It can also be an
 * interface or a normal class.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java">JournalDev
 *      Abstract Factory Design Pattern in Java.</a>
 *
 */
public abstract class Computer {

	public abstract String getRAM();

	public abstract String getHDD();

	public abstract String getCPU();

	@Override
	public String toString() {
		return "RAM= " + this.getRAM() + ", HDD=" + this.getHDD() + ", CPU=" + this.getCPU();
	}
}