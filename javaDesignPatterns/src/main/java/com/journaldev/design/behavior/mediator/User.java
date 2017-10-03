package com.journaldev.design.behavior.mediator;

/**
 * This is the Colleague interface. On the Mediator Design Pattern, the
 * Colleagues are the system objects that communicate with each other. It this
 * case, this class can send and receive messages.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1730/mediator-design-pattern-java">JournalDev
 *      Mediator Java Design Pattern</a>
 */
public abstract class User {
	protected ChatMediator chatMediator;
	protected String name;

	public User(ChatMediator chatMediator, String name) {
		this.chatMediator = chatMediator;
		this.name = name;
	}

	public abstract void send(String message);

	public abstract void receive(String message);
}