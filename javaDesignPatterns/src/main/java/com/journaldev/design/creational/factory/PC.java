package com.journaldev.design.creational.factory;

/**
 * This a is sub class of the factory design pattern. It extends the
 * {@link Computer} class.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1392/factory-design-pattern-in-java">JournalDev
 *      Factory Design Pattern in Java.</a>
 *
 */
public class PC extends Computer {

	private String ram;
	private String hdd;
	private String cpu;

	public PC(String ram, String hdd, String cpu) {
		this.ram = ram;
		this.hdd = hdd;
		this.cpu = cpu;
	}

	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}