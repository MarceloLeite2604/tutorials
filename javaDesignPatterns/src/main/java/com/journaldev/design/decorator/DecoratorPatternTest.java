package com.journaldev.design.decorator;

/**
 * The Decorator design pattern is useful when some characteristics of an object
 * must be defined at runtime without affecting the other instances of other
 * objects of the same type.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1540/decorator-design-pattern-in-java-example">JournalDev
 *      Decorator Design Pattern</a>
 */
public class DecoratorPatternTest {

	public static void main(String[] args) {
		Car sportsCar = new SportsCar(new BasicCar());
		sportsCar.assemble();
		System.out.println("\n*****");

		Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
		sportsLuxuryCar.assemble();
	}

}