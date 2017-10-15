package com.journaldev.design.behavior.visitor;

/**
 * This interface defines the method used throughout the objects visited.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1769/visitor-design-pattern-java">JournalDev
 *      Visitor Pattern Java Example.</a>
 *
 */
public interface ItemElement {

	public int accept(ShoppingCartVisitor shoppingCartVisitor);

}