package com.journaldev.design.behavior.command;

/**
 * This is a concrete implementation of a command (defined by the
 * {@link Command} interface). It also contains a receiver
 * ({@link FileSystemReceiver}), which will execute the command requested.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1624/command-design-pattern">JournalDev
 *      Command design pattern Java example.</a>
 */
public class WriteFileCommand implements Command {

	private FileSystemReceiver fileSystem;

	public WriteFileCommand(FileSystemReceiver fileSystemReceiver) {
		this.fileSystem = fileSystemReceiver;
	}

	@Override
	public void execute() {
		// Write command is forwarding request to writeFile method.
		this.fileSystem.writeFile();
	}

}