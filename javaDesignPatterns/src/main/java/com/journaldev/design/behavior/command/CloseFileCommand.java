package com.journaldev.design.behavior.command;

public class CloseFileCommand implements Command {

	private FileSystemReceiver fileSystem;

	public CloseFileCommand(FileSystemReceiver fileSystemReceiver) {
		this.fileSystem = fileSystemReceiver;
	}

	@Override
	public void execute() {
		// Close command is forwarding request to closeFile method.
		this.fileSystem.closeFile();
	}

}