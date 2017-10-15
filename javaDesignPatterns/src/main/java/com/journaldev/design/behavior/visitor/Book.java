package com.journaldev.design.behavior.visitor;

/**
 * This class implements the visitor interface ({@link ItemElement}).
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1769/visitor-design-pattern-java">JournalDev
 *      Visitor Pattern Java Example.</a>
 *
 */
public class Book implements ItemElement {

	private int price;
	private String isbnNumber;

	public Book(int price, String isbnNumber) {
		this.price = price;
		this.isbnNumber = isbnNumber;
	}

	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor shoppingCartVisitor) {
		return shoppingCartVisitor.visit(this);
	}

}