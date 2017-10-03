package com.journaldev.design.behavior.template;

/**
 * This is the Template Method Abstract Class. It defines the
 * {@link HouseTemplate#buildHouse()} method which defines the execution order
 * or the remaining methods and requires that classes that inherits it
 * implements the remaining methods.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1763/template-method-design-pattern-in-java">JournalDev
 *      Template Method Design Pattern</a>
 */
public abstract class HouseTemplate {

	/**
	 * This is the template method. It defines the execution order of the remaining
	 * methods. It is declared final so the classes that inherits the
	 * {@link HouseTemplate} class does not modify it.
	 */
	public final void buildHouse() {
		// Execution steps: Foundation, pillars, walls and windows.
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();

		System.out.println("House is built.");
	}

	/**
	 * This is an example of a default method. It does not require that classes who
	 * inherits {@link HouseTemplate} implements it.
	 */
	private void buildWindows() {
		System.out.println("Building Glass Windows");
	}

	/**
	 * This is another example of a default method.
	 */
	private void buildFoundation() {
		System.out.println("Building foundation with cement, iron rods and sand");
	}

	/**
	 * This method must be implemented by the classes who inherits the
	 * {@link HouseTemplate} class.
	 */
	public abstract void buildWalls();

	/**
	 * This method must be implemented by the classes who inherits the
	 * {@link HouseTemplate} class.
	 */
	public abstract void buildPillars();

}