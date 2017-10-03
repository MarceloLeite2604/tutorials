package com.journaldev.design.behavior.template;

/**
 * The Template Method Design Pattern defines the steps to execute an algorithm
 * and it can provide default implementation that might be common for all or
 * some of the subclasses.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1763/template-method-design-pattern-in-java">JournalDev
 *      Template Design Pattern</a>
 */
public class TemplatePatternTest {

	public static void main(String[] args) {

		HouseTemplate houseType = new WoodenHouse();

		// Executes the template method, which will execute the methods on the order
		// specified. Since the object instantiated is a WoodenHouse, the "buildWalls"
		// and "buildPillars" of this execution will be from the WoodenHouse class
		// specific.
		houseType.buildHouse();
		System.out.println("************");

		houseType = new GlassHouse();

		// Same as before, but this time the object instantiated is a GlassHouse, so
		// "buildWalls"
		// and "buildPillars" of this execution will be from the GlassHouse class.
		// In either object instantiation, the execution order is the same, defined by
		// the HouseTemplate class.
		houseType.buildHouse();
	}

}