package com.journaldev.design.adapter;

public class SocketObjectAdapterImpl implements SocketAdapter {

	// Using Composition for adapter pattern
	private Socket socket;

	public SocketObjectAdapterImpl(Socket socket) {
		this.socket = socket;
	}

	@Override
	public Volt get120Volt() {
		Volt volt = socket.getVolt();
		return convertVolt(volt, volt.getVolts() / 120);
	}

	@Override
	public Volt get12Volt() {
		Volt volt = socket.getVolt();
		return convertVolt(volt, volt.getVolts() / 12);
	}

	@Override
	public Volt get3Volt() {
		Volt volt = socket.getVolt();
		return convertVolt(volt, volt.getVolts() / 3);
	}

	private Volt convertVolt(Volt volts, int ratio) {
		return new Volt(volts.getVolts() / ratio);
	}

}
