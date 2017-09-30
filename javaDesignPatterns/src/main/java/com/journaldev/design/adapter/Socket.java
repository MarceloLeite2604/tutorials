package com.journaldev.design.adapter;

public class Socket {

	private Volt volt;

	public Socket(int voltage) {
		this.volt = new Volt(voltage);
	}

	public Volt getVolt() {
		return volt;
	}
}