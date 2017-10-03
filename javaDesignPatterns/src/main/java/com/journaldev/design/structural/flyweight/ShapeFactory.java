package com.journaldev.design.structural.flyweight;

import java.util.HashMap;

/**
 * This class implements the flyweight factory. A factory to build the
 * {@link Line} and {@link Oval} heavy objects. It creates the first object, and
 * then returns the same object next time it is required.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1562/flyweight-design-pattern-java">JournalDev
 *      Flyweight Design Pattern</a>
 */
public class ShapeFactory {

	/**
	 * A hash map to store the already instantiated objects.
	 */
	private static final HashMap<ShapeType, Shape> shapeHashMap = new HashMap<ShapeType, Shape>();

	/**
	 * <p>
	 * This method returns an instance of the requested object.
	 * </p>
	 * <p>
	 * On the first time a shape is requested, this method will instantiate it and
	 * store on its shape hash map. Next time the same shape type is requested, it
	 * won't instantiate it, but instead it will return the same previously
	 * instantiated object.
	 * </p>
	 * <p>
	 * It is important to know that, when using this design pattern, each object
	 * must contain only its intrinsic properties. Objects of values which defines
	 * its relation with another objects cannot be stored on it, since the same
	 * object will be shared among others.
	 * 
	 * @param shapeType
	 *            Type of the shape to return.
	 * @return A new {@link Shape} object with the specified {@link ShapeType}.
	 */
	public static Shape getShape(ShapeType shapeType) {

		// Checks if the requested shape type is already instantiated.
		Shape shape = shapeHashMap.get(shapeType);

		// If the requested object is not yet instantiated.
		if (shape == null) {
			// Creates an instance of the requested shape.
			switch (shapeType) {
			case LINE:
				shape = new Line();
				break;
			case OVAL_FILL:
				shape = new Oval(true);
				break;
			case OVAL_NOFILL:
				shape = new Oval(false);
				break;
			default:
				break;
			}
			// Stores the requested shape on the shape hash map.
			shapeHashMap.put(shapeType, shape);
		}
		// Returns the requested shape object instance.
		return shape;
	}

	/**
	 * Enumeration of the types of shape the {@link ShapeFactory} can create.
	 */
	public static enum ShapeType {
		OVAL_FILL, OVAL_NOFILL, LINE;
	}
}