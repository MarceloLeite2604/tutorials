package com.journaldev.design.behavior.observer;

/**
 * The interface that defines the contract methods to be implemented by any
 * observer subject.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1739/observer-design-pattern-in-java">JournalDev
 *      Observer Design Pattern Example</a>
 */
public interface Observer {

	/**
	 * Updates the observer, used by subject.
	 */
	public void update();

	/**
	 * Attaches with subject to observe
	 * 
	 * @param subject
	 *            The subject to observe.
	 */
	public void setSubject(Subject subject);
}