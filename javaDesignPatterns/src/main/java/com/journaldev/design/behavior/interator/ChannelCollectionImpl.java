package com.journaldev.design.behavior.interator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This is the implementation of the {@link ChannelCollection}. Inside of it
 * there is an inner class that implements the {@link ChannelIterator}. By
 * implementing it inside, it ensures that only {@link ChannelCollectionImpl}
 * can create and use it. On the outside, no object can create it.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1716/iterator-design-pattern-java">JournalDev
 *      Iterator Design Pattern Java Example.</a>
 *
 */
public class ChannelCollectionImpl implements ChannelCollection {

	private List<Channel> channelsList;

	public ChannelCollectionImpl() {
		channelsList = new ArrayList<>();
	}

	public void addChannel(Channel channel) {
		this.channelsList.add(channel);
	}

	public void removeChannel(Channel channel) {
		this.channelsList.remove(channel);
	}

	@Override
	public ChannelIterator iterator(ChannelType channelType) {
		return new ChannelIteratorImpl(channelType, this.channelsList);
	}

	/**
	 * <p>
	 * This is the implementation of the {@link ChannelIterator}.
	 * </p>
	 * 
	 * @see <a href=
	 *      "https://www.journaldev.com/1716/iterator-design-pattern-java">JournalDev
	 *      Iterator Design Pattern Java Example.</a>
	 *
	 */
	private class ChannelIteratorImpl implements ChannelIterator {

		private ChannelType type;
		private List<Channel> channels;
		private int position;

		public ChannelIteratorImpl(ChannelType channelType, List<Channel> channelsList) {
			this.type = channelType;
			this.channels = channelsList;
		}

		@Override
		public boolean hasNext() {
			while (position < channels.size()) {
				Channel channel = channels.get(position);
				if (channel.getChannelType().equals(type) || type.equals(ChannelType.ALL)) {
					return true;
				} else
					position++;
			}
			return false;
		}

		@Override
		public Channel next() {
			Channel channel = channels.get(position);
			position++;
			return channel;
		}

	}
}