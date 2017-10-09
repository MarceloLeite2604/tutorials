package com.journaldev.design.behavior.strategy;

/**
 * This is on of the concrete implementations of the algorithm the user can
 * choose on the Strategy design pattern. It must implement the
 * {@link PaymentStrategy} interface.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial">JournalDev
 *      Strategy design pattern Java example</a>
 * 
 */
public class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;

	public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.dateOfExpiry = dateOfExpiry;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid with credit/debit card");
	}

}