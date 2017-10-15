package com.journaldev.design.behavior.visitor;

/**
 * This interface defines the object which will make the visit. It can have a
 * method signature for each type of object visited. Alternatively, it can have
 * implementation for some specific type of objects and a generic one as the
 * default visit algorithm.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1769/visitor-design-pattern-java">JournalDev
 *      Visitor Pattern Java Example.</a>
 *
 */
public interface ShoppingCartVisitor {

	int visit(Book book);

	int visit(Fruit fruit);

	int visit(ItemElement itemElement);
}