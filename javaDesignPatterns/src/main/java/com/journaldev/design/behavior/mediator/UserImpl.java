package com.journaldev.design.behavior.mediator;

/**
 * This is the Concrete Colleague. It implements the Colleague interface.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1730/mediator-design-pattern-java">JournalDev
 *      Mediator Java Design Pattern</a>
 *
 */
public class UserImpl extends User {

	public UserImpl(ChatMediator chatMediator, String name) {
		super(chatMediator, name);
	}

	@Override
	public void send(String message) {
		System.out.println(this.name + ": Sending Message=" + message);
		chatMediator.sendMessage(message, this);
	}

	@Override
	public void receive(String message) {
		System.out.println(this.name + ": Received Message:" + message);
	}

}