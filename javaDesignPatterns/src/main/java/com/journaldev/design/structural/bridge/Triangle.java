package com.journaldev.design.structural.bridge;

/**
 * This is an implementation of the first end of the bridge (the {@link Shape}
 * abstract class), which contains a connector to the other end of the bridge.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1491/bridge-design-pattern-java">JournalDev
 *      Java Bridge Design Pattern</a>
 *
 */
public class Triangle extends Shape {

	public Triangle(Color color) {
		super(color);
	}

	@Override
	public void applyColor() {
		System.out.print("Triangle filled with color ");
		color.applyColor();
	}

}