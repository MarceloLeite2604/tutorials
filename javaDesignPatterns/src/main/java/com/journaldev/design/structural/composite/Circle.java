package com.journaldev.design.structural.composite;

/**
 * This is a leaf element of the composite design pattern. It implements the
 * base component.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1535/composite-design-pattern-in-java">JournalDev
 *      Composite Design Pattern</a>
 */
public class Circle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing circle with color " + fillColor);
	}

}