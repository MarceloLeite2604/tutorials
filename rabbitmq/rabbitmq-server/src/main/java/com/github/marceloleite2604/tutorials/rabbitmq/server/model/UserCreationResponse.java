package com.github.marceloleite2604.tutorials.rabbitmq.server.model;

import java.util.List;
import java.util.Collections;

public class UserCreationResponse {

	private UserCreationStatus status;
	
	private String summary;
	
	private List<String> messages;
	
	private User user;

	private UserCreationResponse(Builder builder) {
		this.status = builder.status;
		this.summary = builder.summary;
		this.messages = builder.messages;
		this.user = builder.user;
	}

	public UserCreationStatus getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}

	public String getSummary() {
		return summary;
	}

	public List<String> getMessages() {
		return messages;
	}

	@Override
	public String toString() {
		return "UserCreationResponse [status=" + status + ", summary=" + summary + ", messages="
				+ messages + ", user=" + user + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private UserCreationStatus status;
		private String summary;
		private List<String> messages = Collections.emptyList();
		private User user;

		private Builder() {
		}

		public Builder status(UserCreationStatus status) {
			this.status = status;
			return this;
		}

		public Builder summary(String summary) {
			this.summary = summary;
			return this;
		}

		public Builder messages(List<String> messages) {
			this.messages = messages;
			return this;
		}

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public UserCreationResponse build() {
			return new UserCreationResponse(this);
		}
	}
}
