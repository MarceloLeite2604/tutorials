package com.journaldev.design.behavior.command;

public class WriteFileCommand implements Command {

	private FileSystemReceiver fileSystem;
	
	public WriteFileCommand(FileSystemReceiver fileSystemReceiver){
		this.fileSystem=fileSystemReceiver;
	}
	@Override
	public void execute() {
		// Write command is forwarding request to writeFile method.		
		this.fileSystem.writeFile();
	}

}