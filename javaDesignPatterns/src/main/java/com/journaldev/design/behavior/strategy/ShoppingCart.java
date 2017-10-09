package com.journaldev.design.behavior.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a shopping cart, where the user can add {@link Item}
 * and choose the payment strategy between {@link CreditCardStrategy} or
 * {@link PaypalStrategy}.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial">JournalDev
 *      Strategy design pattern Java example</a>
 *
 */
public class ShoppingCart {

	// List of items
	List<Item> items;

	public ShoppingCart() {
		this.items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public int calculateTotal() {
		int sum = 0;
		for (Item item : items) {
			sum += item.getPrice();
		}
		return sum;
	}

	public void pay(PaymentStrategy paymentMethod) {
		int amount = calculateTotal();
		paymentMethod.pay(amount);
	}
}