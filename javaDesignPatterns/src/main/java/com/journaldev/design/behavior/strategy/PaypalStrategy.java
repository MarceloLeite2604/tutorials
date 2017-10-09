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
public class PaypalStrategy implements PaymentStrategy {

	private String emailId;
	private String password;

	public PaypalStrategy(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Paypal.");
	}

}