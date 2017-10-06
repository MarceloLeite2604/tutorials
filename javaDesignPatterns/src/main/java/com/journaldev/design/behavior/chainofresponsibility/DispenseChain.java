package com.journaldev.design.behavior.chainofresponsibility;

/**
 * The base interface should have a method to define the next processor in the
 * chain and the method that will process the request.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java">JournalDev
 *      Chain Of Responsibility Design Pattern Example</a>
 *
 */
public interface DispenseChain {

	/**
	 * Defines the next object on the chain.
	 * 
	 * @param nextChain
	 *            The next object on the chain.
	 */
	void setNextChain(DispenseChain nextChain);

	/**
	 * The method that will process the request.
	 * 
	 * @param currency The object to be analyzed by the objects on the chain.
	 */
	void dispense(Currency currency);
}