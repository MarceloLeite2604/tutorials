package com.journaldev.design.behavior.interator;

/**
 * <p>
 * The interface defines the contract for our collection class implementation.
 * It requests methods to include
 * ({@link ChannelCollection#addChannel(Channel)}) and exclude
 * ({@link ChannelCollection#removeChannel(Channel)}) channels. It also request
 * a method to return an iterator based on the channel type
 * ({@link ChannelCollection#iterator(ChannelType)})
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1716/iterator-design-pattern-java">JournalDev
 *      Iterator Design Pattern Java Example.</a>
 *
 */
public interface ChannelCollection {

	public void addChannel(Channel channel);

	public void removeChannel(Channel channel);

	public ChannelIterator iterator(ChannelType channelType);

}