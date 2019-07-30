package com.github.marceloleite2604.tutorials.modelmapper.model;


public class ErrorInformation {

	private String instant;

	private String path;

	private int status;

	private String message;

	private String stackTrace;

	private ErrorInformation(Builder builder) {
		this.instant = builder.instant;
		this.path = builder.path;
		this.status = builder.status;
		this.message = builder.message;
		this.stackTrace = builder.stackTrace;
	}

	public String getInstant() {
		return instant;
	}

	public void setInstant(String instant) {
		this.instant = instant;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String instant;
		private String path;
		private int status;
		private String message;
		private String stackTrace;

		private Builder() {
		}

		public Builder instant(String instant) {
			this.instant = instant;
			return this;
		}

		public Builder path(String path) {
			this.path = path;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder stackTrace(String stackTrace) {
			this.stackTrace = stackTrace;
			return this;
		}

		public ErrorInformation build() {
			return new ErrorInformation(this);
		}
	}
}
