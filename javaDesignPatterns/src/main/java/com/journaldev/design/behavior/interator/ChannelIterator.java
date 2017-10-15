package com.journaldev.design.behavior.interator;

/**
 * <p>
 * This interface defines the iterator contract. It requests the implementation
 * of a method informing if there is another object to return
 * ({@link ChannelIterator#hasNext()}) and a method to return the next object
 * ({@link ChannelIterator#next()})
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1716/iterator-design-pattern-java">JournalDev
 *      Iterator Design Pattern Java Example.</a>
 *
 */
public interface ChannelIterator {

	public boolean hasNext();

	public Channel next();
}