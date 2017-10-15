package com.journaldev.design.behavior.state;

/**
 * <p>
 * This is one of the possible states of our object.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1751/state-design-pattern-java">JournalDev
 *      State Design Pattern Java Tutorial</a>
 *
 */
public class TVStopState implements State {

	@Override
	public void doAction() {
		System.out.println("TV is turned OFF");
	}

}