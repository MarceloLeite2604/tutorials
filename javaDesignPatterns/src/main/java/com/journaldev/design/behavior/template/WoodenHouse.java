package com.journaldev.design.behavior.template;

/**
 * This concrete class inherits the {@link HouseTemplate} class, so it must
 * implement the {@link HouseTemplate#buildPillars()} and
 * {@link HouseTemplate#buildWalls()} methods.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1763/template-method-design-pattern-in-java">JournalDev
 *      Template Method Design Pattern</a>
 */
public class WoodenHouse extends HouseTemplate {

	@Override
	public void buildWalls() {
		System.out.println("Building Wooden Walls");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Wood coating");
	}

}