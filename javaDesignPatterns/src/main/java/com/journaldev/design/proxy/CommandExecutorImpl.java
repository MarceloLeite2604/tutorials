package com.journaldev.design.proxy;

import java.io.IOException;

/**
 * This class implements the {@link CommandExecutor} interface without verifying
 * if the current user is allowed to execute the
 * {@link CommandExecutor#runCommand(String)} method.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1572/proxy-design-pattern">JournalDev Proxy
 *      Design Pattern</a>
 */
public class CommandExecutorImpl implements CommandExecutor {

	@Override
	public void runCommand(String command) throws IOException {
		System.out.println("'" + command + "' command executed.");
	}

}