package com.journaldev.design.behavior.command;

/**
 * This interface defines the receiver contract. It demands to whoever uses it
 * to implement the methods required to receive the commands.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1624/command-design-pattern">JournalDev
 *      Command design pattern Java example.</a>
 *
 */
public interface FileSystemReceiver {

	void openFile();

	void writeFile();

	void closeFile();
}