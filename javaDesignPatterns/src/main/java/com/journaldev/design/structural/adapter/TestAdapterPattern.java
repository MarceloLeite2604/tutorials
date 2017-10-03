package com.journaldev.design.structural.adapter;

/**
 * As the name goes, the adapter design pattern works as an adapter between two
 * originally incompatible objects. The adapter works as a "translator" between
 * them. In this example it is 
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1487/adapter-design-pattern-java">JournalDev
 *      Adapter Design Pattern</a>
 *
 */
public class TestAdapterPattern {

	public static void main(String[] args) {
		testClassAdapter();
		testObjectAdapter();
	}

	private static void testObjectAdapter() {
		// Using a 120 volt socket.
		Socket socket = new Socket(120);
		SocketAdapter socketAdapter = new SocketObjectAdapterImpl(socket);
		Volt volt3 = getVolt(socketAdapter, 3);
		Volt volt12 = getVolt(socketAdapter, 12);
		Volt volt120 = getVolt(socketAdapter, 120);
		System.out.println("volt3 volts using Object Adapter=" + volt3.getVolts());
		System.out.println("volt12 volts using Object Adapter=" + volt12.getVolts());
		System.out.println("volt120 volts using Object Adapter=" + volt120.getVolts());

		// Using a 240 volt socket.
		socket = new Socket(240);
		socketAdapter = new SocketObjectAdapterImpl(socket);
		volt3 = getVolt(socketAdapter, 3);
		volt12 = getVolt(socketAdapter, 12);
		volt120 = getVolt(socketAdapter, 120);
		System.out.println("volt3 volts using Object Adapter=" + volt3.getVolts());
		System.out.println("volt12 volts using Object Adapter=" + volt12.getVolts());
		System.out.println("volt120 volts using Object Adapter=" + volt120.getVolts());
	}

	private static void testClassAdapter() {

		// Using a 120 volt socket.
		Socket socket = new Socket(120);
		SocketAdapter sockAdapter = new SocketClassAdapterImpl(socket);
		Volt volt3 = getVolt(sockAdapter, 3);
		Volt volt12 = getVolt(sockAdapter, 12);
		Volt volt120 = getVolt(sockAdapter, 120);
		System.out.println("volt3 volts using Class Adapter=" + volt3.getVolts());
		System.out.println("volt12 volts using Class Adapter=" + volt12.getVolts());
		System.out.println("volt120 volts using Class Adapter=" + volt120.getVolts());

		// Using a 240 volt socket.
		socket = new Socket(120);
		sockAdapter = new SocketClassAdapterImpl(socket);
		volt3 = getVolt(sockAdapter, 3);
		volt12 = getVolt(sockAdapter, 12);
		volt120 = getVolt(sockAdapter, 120);
		System.out.println("volt3 volts using Class Adapter=" + volt3.getVolts());
		System.out.println("volt12 volts using Class Adapter=" + volt12.getVolts());
		System.out.println("volt120 volts using Class Adapter=" + volt120.getVolts());
	}

	private static Volt getVolt(SocketAdapter sockAdapter, int voltage) {
		switch (voltage) {
		case 3:
			return sockAdapter.get3Volt();
		case 12:
			return sockAdapter.get12Volt();
		case 120:
			return sockAdapter.get120Volt();
		default:
			return sockAdapter.get120Volt();
		}
	}
}
