package com.journaldev.design.behavior.mediator;

/**
 * The Mediator Pattern allows loose coupling by encapsulating the way different
 * sets of objects interact and communicate with each other. Allows for the
 * actions of each object set to vary independently of one another.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1730/mediator-design-pattern-java">JournalDev
 *      Mediator Java Design Pattern</a>
 *
 */
public class MediatorPatternTest {

	public static void main(String[] args) {
		ChatMediator chatMediator = new ChatMediatorImpl();

		// Creates the colleagues.
		User pankaj = new UserImpl(chatMediator, "Pankaj");
		User lisa = new UserImpl(chatMediator, "Lisa");
		User saurabh = new UserImpl(chatMediator, "Saurabh");
		User david = new UserImpl(chatMediator, "David");

		// Adds the colleagues on the mediator.
		chatMediator.addUser(pankaj);
		chatMediator.addUser(lisa);
		chatMediator.addUser(saurabh);
		chatMediator.addUser(david);

		// A message sending test.
		pankaj.send("Hi All");

	}

}