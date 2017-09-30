package com.journaldev.design.composite;

/**
 * This is a leaf element of the composite design pattern. It implements the
 * base component.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1535/composite-design-pattern-in-java">JournalDev
 *      Composite Design Pattern</a>
 */
public class Triangle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing triangle with color " + fillColor);
	}

}
