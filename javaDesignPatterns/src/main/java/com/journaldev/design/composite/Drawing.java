package com.journaldev.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the composite object from the composite design pattern. It contains a
 * group of leaf objects.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1535/composite-design-pattern-in-java">JournalDev
 *      Composite Design Pattern</a>
 */
public class Drawing implements Shape {

	// Collection of shapes.
	private List<Shape> shapeList = new ArrayList<Shape>();

	@Override
	public void draw(String fillColor) {
		for (Shape shape : shapeList) {
			shape.draw(fillColor);
		}
	}

	// Adds the shape to the drawing.
	public void add(Shape shape) {
		this.shapeList.add(shape);
	}

	// Removes the shape to the drawing.
	public void remove(Shape shape) {
		shapeList.remove(shape);
	}

	// Removes all the shapes from the drawing.
	public void clear() {
		System.out.println("Clearing all the shapes from drawing");
		this.shapeList.clear();
	}
}