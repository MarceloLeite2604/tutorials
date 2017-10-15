package com.journaldev.design.behavior.state;

/**
 * This is an example of the project implementation without using the design
 * pattern. Notice how {@link TVRemoteBasic} is tight coupled, since the client
 * code {@link TVRemoteBasic#main(String[])} must know the specific values of
 * {@link TVRemoteBasic} states to define what command must be executed. Also,
 * if the number of states increases, the client code must be updated to work
 * with the new states.
 */
public class TVRemoteBasic {

	private String state = "";

	public void setState(String state) {
		this.state = state;
	}

	public void doAction() {
		if (state.equalsIgnoreCase("ON")) {
			System.out.println("TV is turned ON");
		} else if (state.equalsIgnoreCase("OFF")) {
			System.out.println("TV is turned OFF");
		}
	}

	public static void main(String args[]) {
		TVRemoteBasic remote = new TVRemoteBasic();

		remote.setState("ON");
		remote.doAction();

		remote.setState("OFF");
		remote.doAction();
	}

}