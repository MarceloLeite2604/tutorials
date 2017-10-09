package com.journaldev.design.behavior.strategy;

/**
 * This interface defines the contract to be used by each algorithm that users
 * can choose on the Strategy Pattern.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial">JournalDev
 *      Strategy design pattern Java example</a>
 */
public interface PaymentStrategy {

	public void pay(int amount);
}