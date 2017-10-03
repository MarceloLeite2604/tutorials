package com.journaldev.design.structural.flyweight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A generic shape interface.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1562/flyweight-design-pattern-java">JournalDev
 *      Flyweight Design Pattern</a>
 */
public interface Shape {

	public void draw(Graphics g, int x, int y, int width, int height, Color color);
}
