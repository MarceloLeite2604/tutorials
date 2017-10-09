package com.journaldev.design.behavior.strategy;

/**
 * Strategy pattern is (also known as Policy pattern) is used when we have
 * multiple algorithm for a specific task and client decides the actual
 * implementation to be used at runtime.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial">JournalDev
 *      Strategy design pattern Java example</a>
 *
 */
public class StrategyPatternTest {

	public static void main(String[] args) {
		ShoppingCart shoppingCart = new ShoppingCart();

		Item item1 = new Item("1234", 10);
		Item item2 = new Item("5678", 40);

		shoppingCart.addItem(item1);
		shoppingCart.addItem(item2);

		// Payment through paypal.
		shoppingCart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

		// Payment through by credit card.
		shoppingCart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
	}

}