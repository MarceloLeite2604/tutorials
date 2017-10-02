package com.journaldev.design.decorator;

/**
 * This class implements the Concrete Decorator. It extends the Decorator
 * ({@link CarDecorator}) and customize the Component Interface ({@link Car})
 * methods by adding its own features before/after request the execution of the
 * same method defined by the Decorator
 *
 * @see <a href=
 *      "https://www.journaldev.com/1540/decorator-design-pattern-in-java-example">JournalDev
 *      Decorator Design Pattern</a>
 */
public class SportsCar extends CarDecorator {

	public SportsCar(Car car) {
		super(car);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print(" Adding features of Sports Car.");
	}
}