package com.journaldev.design.flyweight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A Oval class which is "heavy" to create.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1562/flyweight-design-pattern-java">JournalDev
 *      Flyweight Design Pattern</a>
 */
public class Oval implements Shape {

	// This is an intrinsic property - A property that each object has for itself
	// (i. e., it is not based on a relation with other objects.
	private boolean fill;

	public Oval(boolean fill) {
		this.fill = fill;
		System.out.println("Creating Oval object with fill=" + fill);
		// Adding time delay
		try {
			Thread.sleep(2000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics circle, int x, int y, int width, int height, Color color) {
		circle.setColor(color);
		circle.drawOval(x, y, width, height);
		if (fill) {
			circle.fillOval(x, y, width, height);
		}
	}

}