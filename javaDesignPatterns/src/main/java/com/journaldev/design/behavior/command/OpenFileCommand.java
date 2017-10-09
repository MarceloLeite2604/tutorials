package com.journaldev.design.behavior.command;

public class OpenFileCommand implements Command {

	private FileSystemReceiver fileSystem;

	public OpenFileCommand(FileSystemReceiver fileSystemReceiver) {
		this.fileSystem = fileSystemReceiver;
	}

	@Override
	public void execute() {
		// Open command is forwarding request to openFile method
		this.fileSystem.openFile();
	}

}