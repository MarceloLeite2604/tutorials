package com.journaldev.design.behavior.memento;

/**
 * Caretaker is the helper class that is responsible for storing and restoring
 * the Originator’s state through Memento object. Since Memento is private to
 * Originator, Caretaker can’t access it and it’s stored as an object within the
 * caretaker.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1734/memento-design-pattern-java">JournalDev
 *      Memento Design Pattern Java Example.</a>
 */
public class FileWriterCaretaker {

	private Object obj;

	public void save(FileWriterUtil fileWriter) {
		this.obj = fileWriter.save();
	}

	public void undo(FileWriterUtil fileWriter) {
		fileWriter.undoToLastSave(obj);
	}
}