package com.journaldev.design.decorator;

/**
 * This is the component interface of the Decorator design pattern. It defines
 * the methods that will be implemented by the component implementation
 * ({@link BasicCar}) and the concrete decorators ({@link SportsCar} and
 * {@link LuxuryCar}).
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1540/decorator-design-pattern-in-java-example">JournalDev
 *      Decorator Design Pattern</a>
 */
public interface Car {

	public void assemble();
}