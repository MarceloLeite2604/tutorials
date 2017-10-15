package com.journaldev.design.behavior.visitor;

/**
 * This class implements the visitor interface ({@link ItemElement}).
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1769/visitor-design-pattern-java">JournalDev
 *      Visitor Pattern Java Example.</a>
 *
 */
public class Fruit implements ItemElement {

	private int pricePerKg;
	private int weight;
	private String name;

	public Fruit(int pricePerKg, int weight, String name) {
		this.pricePerKg = pricePerKg;
		this.weight = weight;
		this.name = name;
	}

	public int getPricePerKg() {
		return pricePerKg;
	}

	public int getWeight() {
		return weight;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int accept(ShoppingCartVisitor shoppingCartVisitor) {
		return shoppingCartVisitor.visit(this);
	}

}