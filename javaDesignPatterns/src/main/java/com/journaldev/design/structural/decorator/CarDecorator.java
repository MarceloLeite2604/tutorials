package com.journaldev.design.structural.decorator;

/**
 * This class implements the link between the components. It implements the
 * Component Interface ({@link Car}) and has a field for another Component
 * Interface. When is Component Interface method is executed, it links the
 * execution with the same method of its field Component Interface.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1540/decorator-design-pattern-in-java-example">JournalDev
 *      Decorator Design Pattern</a>
 */
public class CarDecorator implements Car {

	protected Car car;

	public CarDecorator(Car car) {
		this.car = car;
	}

	@Override
	public void assemble() {
		this.car.assemble();
	}

}