package com.journaldev.design.behavior.interpreter;

/**
 * The intent of the Interpreter Design Pattern is to, given a certain language,
 * define a representation for its grammar along with an interpreter that uses
 * the representation to interpret sentences in the language. It also maps a
 * domain to a language, the language to a grammar, and the grammar to a
 * hierarchical object-oriented design.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1635/interpreter-design-pattern-java">JournalDev
 *      Interpreter Pattern Java Example.</a>
 * @see <a href= "https://sourcemaking.com/design_patterns/interpreter">Source
 *      Making Interpreter Design Pattern.</a>
 * @see <a href="https://en.wikipedia.org/wiki/Interpreter_pattern">Wikipedia:
 *      Interpreter Pattern</a>
 *
 */
public class InterpreterPatternTest {

	public static void main(String args[]) {
		String firstText = "28 in Binary";
		String secondText = "28 in Hexadecimal";

		Interpreter interpreter = new Interpreter(new InterpreterContext());
		System.out.println(firstText + "= " + interpreter.interpret(firstText));
		System.out.println(secondText + "= " + interpreter.interpret(secondText));

	}
}