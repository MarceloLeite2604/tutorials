package com.journaldev.design.behavior.memento;

/**
 * Memento design pattern is used when we want to save the state of an object so
 * that we can restore later on. Memento pattern is used to implement this in
 * such a way that the saved state data of the object is not accessible outside
 * of the object, this protects the integrity of saved state data.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1734/memento-design-pattern-java">JournalDev
 *      Memento Design Pattern Java Example.</a>
 *
 */
public class MementoPatternTest {

	public static void main(String[] args) {

		FileWriterCaretaker fileWriterCaretaker = new FileWriterCaretaker();

		FileWriterUtil fileWriterUtil = new FileWriterUtil("data.txt");
		fileWriterUtil.write("First Set of Data\n");
		System.out.println(fileWriterUtil + "\n\n");

		// Lets save the file
		fileWriterCaretaker.save(fileWriterUtil);

		// Now write something else
		fileWriterUtil.write("Second Set of Data\n");

		// Checking file contents
		System.out.println(fileWriterUtil + "\n\n");

		// Lets undo to last save
		fileWriterCaretaker.undo(fileWriterUtil);

		// Checking file content again
		System.out.println(fileWriterUtil + "\n\n");

	}

}