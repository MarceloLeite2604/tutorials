package com.journaldev.design.behavior.interator;

/**
 * <p>
 * This is the object to be stored on the collection.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1716/iterator-design-pattern-java">JournalDev
 *      Iterator Design Pattern Java Example.</a>
 *
 */
public class Channel {

	private double frequency;
	private ChannelType channelType;

	public Channel(double frequency, ChannelType channelType) {
		this.frequency = frequency;
		this.channelType = channelType;
	}

	public double getFrequency() {
		return frequency;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	@Override
	public String toString() {
		return "Frequency=" + this.frequency + ", Type=" + this.channelType;
	}

}