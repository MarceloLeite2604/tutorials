package com.journaldev.design.behavior.interpreter;

/**
 * <p>
 * This is the interface for expressions that will request the interpretation of
 * the input. Also, an expression can be terminal or non-terminal. The former is
 * an object which has no child on the AST and interprets an expression
 * directly. The later maintains a container of child expressions and forwards
 * interpretation requests to these expressions.
 * </p>
 * <p>
 * Observation: The group of expressions of a language defines its grammar.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1635/interpreter-design-pattern-java">JournalDev
 *      Interpreter Pattern Java Example.</a>
 *
 */
public interface Expression {

	String interpret(InterpreterContext interpreterContext);
}