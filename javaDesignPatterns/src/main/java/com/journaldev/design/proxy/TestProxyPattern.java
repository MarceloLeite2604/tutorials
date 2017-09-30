package com.journaldev.design.proxy;

/**
 * This class tests the proxy design pattern. According to
 * <a href="https://dzone.com/articles/design-patterns-proxy">DZone Java Proxy
 * Pattern Tutorial</a> a proxy pattern is useful when either of the following
 * scenarios occur:
 * <ul>
 * <li>The object being represented is external to the system;</li>
 * <li>Objects need to be created on demand;</li>
 * <li>Access control for the original object is required;</li>
 * <li>Added functionality is required when an object is accessed.</li>
 * </ul>
 *
 * @see <a href=
 *      "https://www.journaldev.com/1572/proxy-design-pattern">JournalDev Proxy
 *      Design Pattern</a>
 */
public class TestProxyPattern {

	public static void main(String[] args) {
		// Declaring "commandExecutor" based on the "CommandExecutor" interface, we can
		// choose which implementation of it we'll use.
		CommandExecutor commandExecutor;

		// Let's try it with a "CommandExecutorImpl" object. This class does not provide
		// any security check.
		commandExecutor = new CommandExecutorImpl();

		// Now let's execute a command. Since no security check is realized, our command
		// will be executed successfully.
		try {
			commandExecutor.runCommand("rm -rf *");
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		// Now let's use the same variable, but use a "CommandExecutorProxy" object on
		// it passing a wrong password for the user.
		commandExecutor = new CommandExecutorProxy("Pankaj", "wrong_pwd");
		try {
			// Since this class has a security validation, the "rm" command won't be
			// allowed, throwing an exception.
			commandExecutor.runCommand("ls -ltr");
			commandExecutor.runCommand(" rm -rf abc.pdf");
		} catch (Exception exception) {
			System.out.println("Exception thrown: " + exception.getMessage());
		}

	}
}
