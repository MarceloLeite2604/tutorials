package com.journaldev.design.behavior.interpreter;

/**
 * This is an implementation of the {@link Expression} interface. It receives
 * the integer and requests its interpretation, translating it from decimal to
 * binary.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1635/interpreter-design-pattern-java">JournalDev
 *      Interpreter Pattern Java Example.</a>
 *
 */
public class IntToBinaryExpression implements Expression {

	private int integer;

	public IntToBinaryExpression(int integer) {
		this.integer = integer;
	}

	@Override
	public String interpret(InterpreterContext interpreterContext) {
		return interpreterContext.getBinaryFormat(this.integer);
	}

}