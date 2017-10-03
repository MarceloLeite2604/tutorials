package com.journaldev.design.structural.adapter;

//Using inheritance for adapter pattern.
public class SocketClassAdapterImpl extends Socket implements SocketAdapter {

	SocketClassAdapterImpl(Socket socket) {
		super(socket.getVolt().getVolts());
	}

	@Override
	public Volt get120Volt() {
		Volt volt = getVolt();
		return convertVolt(volt, volt.getVolts() / 120);
	}

	@Override
	public Volt get12Volt() {
		Volt volt = getVolt();
		return convertVolt(volt, volt.getVolts() / 12);
	}

	@Override
	public Volt get3Volt() {
		Volt volt = getVolt();
		return convertVolt(volt, volt.getVolts() / 3);
	}

	private Volt convertVolt(Volt volts, int ratio) {
		return new Volt(volts.getVolts() / ratio);
	}
}
