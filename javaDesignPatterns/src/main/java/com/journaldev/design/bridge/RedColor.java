package com.journaldev.design.bridge;

/**
 * This is an implementation of the second end of the bridge ({@link Color}),
 * which is declared the first end of the bridge ({@link Shape}).
 *
 * @see <a href=
 *      "https://www.journaldev.com/1491/bridge-design-pattern-java">JournalDev
 *      Java Bridge Design Pattern</a>
 *
 */
public class RedColor implements Color {

	public void applyColor() {
		System.out.println("red.");
	}
}