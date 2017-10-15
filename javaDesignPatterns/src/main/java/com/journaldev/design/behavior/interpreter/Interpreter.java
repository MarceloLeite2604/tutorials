package com.journaldev.design.behavior.interpreter;

/**
 * <p>
 * The interpreter is responsible to represent a sentence in the language by an
 * abstract syntax tree (AST) made up by {@link Expression} instances and
 * request its interpretation by calling the
 * {@link Expression#interpret(InterpreterContext)} on the AST. The interpreter
 * does not describe how to build an abstract syntax tree. This can be done
 * either manually by a client or automatically by a parser.
 * </p>
 * <p>
 * Observation: This implementation does not create the AST, it just extracts
 * the expression for its interpretation.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1635/interpreter-design-pattern-java">JournalDev
 *      Interpreter Pattern Java Example.</a>
 * @see <a href= "https://sourcemaking.com/design_patterns/interpreter">Source
 *      Making Interpreter Design Pattern.</a>
 * @see <a href="https://en.wikipedia.org/wiki/Interpreter_pattern">Wikipedia:
 *      Interpreter Pattern</a>
 */
public class Interpreter {
	public InterpreterContext interpreterContext;

	public Interpreter(InterpreterContext interpreterContext) {
		this.interpreterContext = new InterpreterContext();
	}

	public String interpret(String text) {
		Expression expression = null;

		String stringInteger = text.substring(0, text.indexOf(" "));

		int integer = Integer.parseInt(stringInteger);

		// Create rules for expressions
		if (text.contains("Hexadecimal")) {
			expression = new IntToHexExpression(integer);
		} else if (text.contains("Binary")) {
			expression = new IntToBinaryExpression(integer);
		} else
			return text;

		return expression.interpret(interpreterContext);
	}
}