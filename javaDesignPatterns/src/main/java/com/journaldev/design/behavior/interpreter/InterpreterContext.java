package com.journaldev.design.behavior.interpreter;

/**
 * This is the interpreter class. It receives the input and return its
 * interpretation.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1635/interpreter-design-pattern-java">JournalDev
 *      Interpreter Pattern Java Example.</a>
 *
 */
public class InterpreterContext {

	public String getBinaryFormat(int integer) {
		return Integer.toBinaryString(integer);
	}

	public String getHexadecimalFormat(int integer) {
		return Integer.toHexString(integer);
	}
}