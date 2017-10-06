package com.journaldev.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Observer design pattern defines a one-to-many dependency between objects
 * so that when one object changes state, all its dependents are notified and
 * updated automatically.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1739/observer-design-pattern-in-java">JournalDev
 *      Observer Design Pattern Example</a>
 */
public class ObserverPatternTest {

	private static final int TOTAL_OF_OBSERVERS = 10;

	public static void main(String[] args) {

		// Create subject
		SubjectImpl subject = new SubjectImpl();

		List<Observer> observerList = new ArrayList<Observer>();

		// Create observers.
		for (int counter = 0; counter < TOTAL_OF_OBSERVERS; counter++) {
			observerList.add(new ObserverImpl("Observer " + counter));
		}

		// Registers observers to the subject
		for (Observer observer : observerList) {
			subject.register(observer);
			observer.setSubject(subject);
		}

		// now send message to subject
		subject.postMessage("New Message");
	}

}