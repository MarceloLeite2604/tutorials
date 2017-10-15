package com.journaldev.design.behavior.visitor;

/**
 * Visitor pattern is used when we have to perform an operation on a group of
 * similar kind of objects. With the help of visitor pattern, we can move the
 * operational logic from the objects to another class.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1769/visitor-design-pattern-java">JournalDev
 *      Visitor Pattern Java Example.</a>
 *
 */
public class VisitorPatternTest {

	public static void main(String[] args) {
		ItemElement[] items = new ItemElement[] { new Book(20, "1234"), new Book(100, "5678"),
				new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple") };

		int total = calculatePrice(items);
		System.out.println("Total Cost = " + total);
	}

	private static int calculatePrice(ItemElement[] items) {
		ShoppingCartVisitor shoppingCartVisitor = new ShoppingCartVisitorImpl();
		int total = 0;
		for (ItemElement item : items) {
			total = total + item.accept(shoppingCartVisitor);
		}
		return total;
	}

}