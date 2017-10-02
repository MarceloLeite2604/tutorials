package com.journaldev.design.bridge;

/**
 * This class is the first end of the bridge on the Bridge design pattern. It
 * contains a field which stores the second end of the bridge ({@link Color}).
 * Since its only purpose the field storage, it can be declared as abstract.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1491/bridge-design-pattern-java">JournalDev
 *      Java Bridge Design Pattern</a>
 *
 */
public abstract class Shape {

	// Composition - implementor
	protected Color color;

	// Constructor with implementor as input argument
	public Shape(Color color) {
		this.color = color;
	}

	abstract public void applyColor();
}