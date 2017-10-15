package com.journaldev.design.behavior.interator;

/**
 * <p>
 * The Iterator design pattern provides a way to access the elements of an
 * aggregate object without exposing its underlying representation. Moreover, it
 * can provide different kind of iterators based on the requirement.
 * </p>
 * <p>
 * The Iterator design pattern hides the actual implementation of traversal
 * through the collection. Client programs just use iterator methods.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1716/iterator-design-pattern-java">JournalDev
 *      Iterator Design Pattern Java Example.</a>
 *
 */
public class IteratorPatternTest {

	public static void main(String[] args) {
		ChannelCollection channelCollection = populateChannels();

		// Iterates through all channels.
		ChannelIterator channelIterator = channelCollection.iterator(ChannelType.ALL);
		while (channelIterator.hasNext()) {
			Channel channel = channelIterator.next();
			System.out.println(channel.toString());
		}
		System.out.println("******");

		// Iterates only through English channels.
		ChannelIterator englishChannelInterator = channelCollection.iterator(ChannelType.ENGLISH);
		while (englishChannelInterator.hasNext()) {
			Channel channel = englishChannelInterator.next();
			System.out.println(channel.toString());
		}
	}

	private static ChannelCollection populateChannels() {
		ChannelCollection channelCollection = new ChannelCollectionImpl();
		channelCollection.addChannel(new Channel(98.5, ChannelType.ENGLISH));
		channelCollection.addChannel(new Channel(99.5, ChannelType.HINDI));
		channelCollection.addChannel(new Channel(100.5, ChannelType.FRENCH));
		channelCollection.addChannel(new Channel(101.5, ChannelType.ENGLISH));
		channelCollection.addChannel(new Channel(102.5, ChannelType.HINDI));
		channelCollection.addChannel(new Channel(103.5, ChannelType.FRENCH));
		channelCollection.addChannel(new Channel(104.5, ChannelType.ENGLISH));
		channelCollection.addChannel(new Channel(105.5, ChannelType.HINDI));
		channelCollection.addChannel(new Channel(106.5, ChannelType.FRENCH));
		return channelCollection;
	}

}