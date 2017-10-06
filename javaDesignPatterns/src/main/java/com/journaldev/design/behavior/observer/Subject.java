package com.journaldev.design.behavior.observer;

/**
 * The interface that defines the contract methods to be implemented by any
 * concrete subject.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1739/observer-design-pattern-in-java">JournalDev
 *      Observer Design Pattern Example</a>
 */
public interface Subject {

	/**
	 * Registers an observer.
	 * 
	 * @param observer
	 *            The observer to register.
	 */
	public void register(Observer observer);

	/**
	 * Unregisters an observer.
	 * 
	 * @param observer
	 *            The observer to unregister.
	 */
	public void unregister(Observer observer);

	/**
	 * Notifies observers of change.
	 */
	public void notifyObservers();

	/**
	 * Gets updates from subject
	 * 
	 * @param observer
	 * @return
	 */
	public Object getUpdate(Observer observer);

}