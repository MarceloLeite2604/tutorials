package com.journaldev.design.behavior.command;

/**
 * A concrete implementation of the receiver interface (@{link
 * {@link FileSystemReceiver}). It implements the methods required to receive
 * the commands defined by the receiver interface.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1624/command-design-pattern">JournalDev
 *      Command design pattern Java example.</a>
 *
 */
public class WindowsFileSystemReceiver implements FileSystemReceiver {

	@Override
	public void openFile() {
		System.out.println("Opening file in Windows OS");

	}

	@Override
	public void writeFile() {
		System.out.println("Writing file in Windows OS");
	}

	@Override
	public void closeFile() {
		System.out.println("Closing file in Windows OS");
	}

}