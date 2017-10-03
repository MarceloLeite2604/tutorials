package com.journaldev.design.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Concrete Mediator class. It implements the Mediator Interface.
 *
 * @see <a href=
 *      "https://www.journaldev.com/1730/mediator-design-pattern-java">JournalDev
 *      Mediator Java Design Pattern</a>
 *
 */
public class ChatMediatorImpl implements ChatMediator {

	private List<User> userList;

	public ChatMediatorImpl() {
		this.userList = new ArrayList<>();
	}

	@Override
	public void addUser(User user) {
		this.userList.add(user);
	}

	@Override
	public void sendMessage(String message, User senderUser) {
		for (User receiverUser : this.userList) {
			// Message should not be received by the user sending it
			if (receiverUser != senderUser) {
				receiverUser.receive(message);
			}
		}
	}

}