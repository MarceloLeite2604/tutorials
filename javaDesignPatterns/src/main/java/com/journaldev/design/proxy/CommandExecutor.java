package com.journaldev.design.proxy;

/**
 * This interface defines the method to be executed.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1572/proxy-design-pattern">JournalDev Proxy
 *      Design Pattern</a>
 */
public interface CommandExecutor {

	public void runCommand(String cmd) throws Exception;
}