package com.journaldev.design.composite;

import com.journaldev.design.composite.Circle;
import com.journaldev.design.composite.Drawing;
import com.journaldev.design.composite.Shape;
import com.journaldev.design.composite.Triangle;

/**
 * The composite design pattern testing class.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1535/composite-design-pattern-in-java">JournalDev
 *      Composite Design Pattern</a>
 */
public class TestCompositePattern {

	public static void main(String[] args) {

		// Creates leaf objects.
		Shape firstTriangle = new Triangle();
		Shape secondTriangle = new Triangle();
		Shape circle = new Circle();

		// Creates a composite object.
		Drawing drawing = new Drawing();

		// Adds the leaves on the composite object.
		drawing.add(secondTriangle);
		drawing.add(secondTriangle);
		drawing.add(circle);

		// Paints the drawing in red.
		drawing.draw("Red");

		// Removes all the leaves from the object.
		drawing.clear();

		// Adds the leaves on the composite object.
		drawing.add(firstTriangle);
		drawing.add(circle);

		// Paints the drawing in green.
		drawing.draw("Green");
	}

}