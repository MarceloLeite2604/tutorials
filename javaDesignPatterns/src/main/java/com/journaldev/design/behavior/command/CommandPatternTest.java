package com.journaldev.design.behavior.command;

/**
 * The Commander design pattern consists of four elements.
 * <ol>
 * <li>The request;</li>
 * <li>The invoker;</li>
 * <li>The command;</li>
 * <li>The receiver.</li>
 * </ol>
 * 
 * <p>
 * The request is send to the invoker, who passes it to the encapsulated Command
 * object. The Command object passes the request to the appropriate method of
 * Receiver to perform the specific action.
 * </p>
 * <p>
 * The Command design pattern is used to implement loose coupling in a
 * request-response model.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1624/command-design-pattern">JournalDev
 *      Command design pattern Java example.</a>
 *
 */
public class CommandPatternTest {

	public static void main(String[] args) {

		// Creating the receiver object.
		FileSystemReceiver fileSystemReceiver = FileSystemReceiverUtil.getUnderlyingFileSystem();

		// 1. Creates the command and associates with receiver.
		OpenFileCommand openFileCommand = new OpenFileCommand(fileSystemReceiver);

		// 2. Creates the invoker and associates with command.
		FileInvoker fileInvoker = new FileInvoker(openFileCommand);

		// 3. Perform action on invoker object.
		fileInvoker.execute();

		// Repeat the steps to write on file.
		WriteFileCommand writeFileCommand = new WriteFileCommand(fileSystemReceiver);
		fileInvoker = new FileInvoker(writeFileCommand);
		fileInvoker.execute();

		// Repeat the steps to close file.
		CloseFileCommand closeFileCommand = new CloseFileCommand(fileSystemReceiver);
		fileInvoker = new FileInvoker(closeFileCommand);
		fileInvoker.execute();
	}

}