package com.journaldev.design.behavior.state;

/**
 * <p>
 * It stores the current state of our object. Either {@link TVStartState} or
 * {@link TVStopState}.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1751/state-design-pattern-java">JournalDev
 *      State Design Pattern Java Tutorial</a>
 *
 */
public class TVContext implements State {

	private State tvState;

	public void setState(State state) {
		this.tvState = state;
	}

	public State getState() {
		return this.tvState;
	}

	@Override
	public void doAction() {
		this.tvState.doAction();
	}

}