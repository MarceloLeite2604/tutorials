package com.journaldev.design.composite;

/**
 * This interface implements the base component of the composite design pattern.
 * It contains the base components used on the leaves to define the common
 * behavior.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1535/composite-design-pattern-in-java">JournalDev
 *      Composite Design Pattern</a>
 *
 */
public interface Shape {

	public void draw(String fillColor);
}