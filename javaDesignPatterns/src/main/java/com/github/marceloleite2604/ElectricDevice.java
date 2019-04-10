package com.github.marceloleite2604;

public class ElectricDevice {
	private String name;
	private int requiredVoltage;

	public ElectricDevice(String name, int requiredVoltage) {
		super();
		this.name = name;
		this.requiredVoltage = requiredVoltage;
	}

	public void connectCharger(int inputVoltage) {
		if (inputVoltage == requiredVoltage) {
			System.out.println("The input voltage is the same as the required to chage this " + name + ".");
		} else {
			System.err.println("The required voltage to charge this " + name + " is " + requiredVoltage
					+ "V, but the input voltage is " + inputVoltage + "V.");
		}
	}
}
