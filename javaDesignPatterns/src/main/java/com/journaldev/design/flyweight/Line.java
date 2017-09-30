package com.journaldev.design.flyweight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A Line class which is "heavy" to create.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1562/flyweight-design-pattern-java">JournalDev
 *      Flyweight Design Pattern</a>
 */
public class Line implements Shape {

	public Line() {
		System.out.println("Creating Line object");
		// Adding time delay to simulate a heavy creation time.
		try {
			Thread.sleep(2000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics line, int x, int y, int width, int height, Color color) {
		line.setColor(color);
		line.drawLine(x, y, width, height);
	}

}