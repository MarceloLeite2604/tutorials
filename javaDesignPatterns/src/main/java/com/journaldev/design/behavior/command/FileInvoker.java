package com.journaldev.design.behavior.command;

/**
 * This is a invoker. It is a simple class that encapsulates a Command
 * ({@link Command}) and passes the request to the command object to process it.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1624/command-design-pattern">JournalDev
 *      Command design pattern Java example.</a>
 *
 */
public class FileInvoker {

	public Command command;

	public FileInvoker(Command command) {
		this.command = command;
	}

	public void execute() {
		this.command.execute();
	}
}