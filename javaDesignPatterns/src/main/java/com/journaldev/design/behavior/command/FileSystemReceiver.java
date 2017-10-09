package com.journaldev.design.behavior.command;

public interface FileSystemReceiver {

	void openFile();
	void writeFile();
	void closeFile();
}