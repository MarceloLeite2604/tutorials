package com.journaldev.design.behavior.command;

/**
 * This interface is the command contract, which defines what should be done
 * with the command.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1624/command-design-pattern">JournalDev
 *      Command design pattern Java example.</a>
 */
public interface Command {

	void execute();
}