package com.journaldev.design.behavior.observer;

/**
 * The concrete implementation of the {@link Observer}.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1739/observer-design-pattern-in-java">JournalDev
 *      Observer Design Pattern Example</a>
 */
public class ObserverImpl implements Observer {

	private String name;
	private Subject subject;

	public ObserverImpl(String name) {
		this.name = name;
	}

	@Override
	public void update() {
		String message = (String) subject.getUpdate(this);
		if (message == null) {
			System.out.println(name + ":: No new message");
		} else
			System.out.println(name + ":: Consuming message::" + message);
	}

	@Override
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}