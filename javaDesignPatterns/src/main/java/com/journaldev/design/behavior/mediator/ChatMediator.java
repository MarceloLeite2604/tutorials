package com.journaldev.design.behavior.mediator;

/**
 * This is the Mediator interface. It defines the contract for Concrete
 * Mediators.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1730/mediator-design-pattern-java">JournalDev
 *      Mediator Java Design Pattern</a>
 */
public interface ChatMediator {

	public void sendMessage(String message, User user);

	void addUser(User user);
}