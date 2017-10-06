package com.journaldev.design.behavior.chainofresponsibility;

/**
 * Stores the amount of currency to dispense.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java">JournalDev
 *      Chain Of Responsibility Design Pattern Example</a>
 *
 */
public class Currency {

	private int amount;

	public Currency(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return this.amount;
	}
}