package org.marceloleite;

import com.journaldev.design.structural.adapter.Socket;
import com.journaldev.design.structural.adapter.SocketObjectAdapterImpl;

/**
 * <p>
 * Using the objects created on the JournalDev adapter pattern tutorial, I've
 * created a simple test to understand this design pattern.
 * </p>
 * <p>
 * The objective is to successfully charge electrical devices using a 120V
 * socket, a 240V socket and a socket adapter. Each electric device is charged
 * by a different voltage, so we need an adapter to convert the socket voltage
 * to the desired input.
 * </p>
 * 
 * @author Marcelo Leite
 *
 */
public class AdapterPatternTest {

	public static void main(String[] args) {

		// Electric devices to charge.
		ElectricDevice computer = new ElectricDevice("Computer", 120);
		ElectricDevice soundSystem = new ElectricDevice("Sound System", 12);
		ElectricDevice bluetoothEarphone = new ElectricDevice("Bluetotoh Earphone", 3);

		// Sockets available.
		Socket socket240v = new Socket(240);
		Socket socket120v = new Socket(120);

		// The adapter used to charge the electric devices.
		SocketObjectAdapterImpl socketAdapter;

		// Lets start connecting the adapter to the 120V socket.
		socketAdapter = new SocketObjectAdapterImpl(socket120v);

		// Now lets use the adapter to charge the computer with 120 volts.
		computer.connectCharger(socketAdapter.get120Volt().getVolts());

		// Now, using the same adapter, lets charge the sound system.
		soundSystem.connectCharger(socketAdapter.get12Volt().getVolts());

		// Finally, lets charge the Bluetooth earphone.
		bluetoothEarphone.connectCharger(socketAdapter.get3Volt().getVolts());

		// Now we'll connect the socket adapter to the 240V socket.
		socketAdapter = new SocketObjectAdapterImpl(socket240v);

		// Let's charge the electric devices.
		computer.connectCharger(socketAdapter.get120Volt().getVolts());
		soundSystem.connectCharger(socketAdapter.get12Volt().getVolts());
		bluetoothEarphone.connectCharger(socketAdapter.get3Volt().getVolts());
	}

}
