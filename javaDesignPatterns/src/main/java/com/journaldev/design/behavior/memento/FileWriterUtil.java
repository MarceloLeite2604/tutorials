package com.journaldev.design.behavior.memento;

/**
 * This is the originator. Originator is the object whose state needs to be
 * saved and restored and it uses an inner class to save the state of object.
 * The inner class is called Memento and it’s private, so that it can’t be
 * accessed from other objects.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1734/memento-design-pattern-java">JournalDev
 *      Memento Design Pattern Java Example.</a>
 */
public class FileWriterUtil {

	private String fileName;
	private StringBuilder content;

	public FileWriterUtil(String fileName) {
		this.fileName = fileName;
		this.content = new StringBuilder();
	}

	@Override
	public String toString() {
		return this.content.toString();
	}

	public void write(String string) {
		content.append(string);
	}

	public Memento save() {
		return new Memento(this.fileName, this.content);
	}

	public void undoToLastSave(Object mementoObject) {
		Memento memento = (Memento) mementoObject;
		this.fileName = memento.fileName;
		this.content = memento.content;
	}

	/**
	 * This is the memento of {@link FileWriterUtil} originator. It stores a state
	 * of the originator. It is declared as an inner class so only the originator
	 * can access it.
	 * 
	 * @see <a href=
	 *      "https://www.journaldev.com/1734/memento-design-pattern-java">JournalDev
	 *      Memento Design Pattern Java Example.</a>
	 *
	 */
	private class Memento {
		private String fileName;
		private StringBuilder content;

		public Memento(String file, StringBuilder content) {
			this.fileName = file;
			// Notice the deep copy so that Memento and FileWriterUtil content variables
			// don't refer to same object
			this.content = new StringBuilder(content);
		}
	}
}