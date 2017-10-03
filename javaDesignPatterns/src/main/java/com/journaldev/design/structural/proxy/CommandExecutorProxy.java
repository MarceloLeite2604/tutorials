package com.journaldev.design.structural.proxy;

/**
 * This class implements the {@link CommandExecutor} interface but, before it,
 * checks if current user is allowed to execute the
 * {@link CommandExecutor#runCommand(String)} method.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1572/proxy-design-pattern">JournalDev Proxy
 *      Design Pattern</a>
 */
public class CommandExecutorProxy implements CommandExecutor {

	private boolean isAdmin;
	private CommandExecutor executor;

	public CommandExecutorProxy(String user, String pwd) {
		if ("Pankaj".equals(user) && "J@urnalD$v".equals(pwd))
			isAdmin = true;
		executor = new CommandExecutorImpl();
	}

	@Override
	public void runCommand(String cmd) throws Exception {
		if (isAdmin) {
			executor.runCommand(cmd);
		} else {
			if (cmd.trim().startsWith("rm")) {
				throw new Exception("rm command is not allowed for non-admin users.");
			} else {
				executor.runCommand(cmd);
			}
		}
	}

}