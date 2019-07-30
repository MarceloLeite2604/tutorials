package com.github.marceloleite2604.tutorials.modelmapper.model;

import java.util.Collections;
import java.util.List;

public class DetailedMessage {

	private String message;

	private List<String> details;

	private DetailedMessage(Builder builder) {
		this.message = builder.message;
		this.details = builder.details;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "DetailedMessage [message=" + message + ", details=" + details + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailedMessage other = (DetailedMessage) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details)) {
			return false;
		}
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String message;
		private List<String> details = Collections.emptyList();

		private Builder() {
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder details(List<String> details) {
			this.details = details;
			return this;
		}

		public DetailedMessage build() {
			return new DetailedMessage(this);
		}
	}
}
