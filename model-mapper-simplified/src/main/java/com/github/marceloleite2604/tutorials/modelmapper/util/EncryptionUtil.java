package com.github.marceloleite2604.tutorials.modelmapper.util;

import java.util.Optional;

import com.github.marceloleite2604.encryptor.EncryptorDecryptor;

public class EncryptionUtil {

	private String key;

	private EncryptorDecryptor encryptorDecryptor;

	private EncryptionUtil(Builder builder) {
		this.key = builder.key;
		this.encryptorDecryptor = builder.encryptorDecryptor;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String encrypt(String content) {
		return Optional.ofNullable(key)
				.map(k -> encryptorDecryptor.encrypt(content, k))
				.orElseGet(() -> encryptorDecryptor.encrypt(content));
	}

	public String decrypt(String content) {
		return Optional.ofNullable(key)
				.map(k -> encryptorDecryptor.decrypt(content, k))
				.orElseGet(() -> encryptorDecryptor.decrypt(content));
	}

	public static final class Builder {
		private String key;
		private EncryptorDecryptor encryptorDecryptor;

		private Builder() {
		}

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder encryptorDecryptor(EncryptorDecryptor encryptorDecryptor) {
			this.encryptorDecryptor = encryptorDecryptor;
			return this;
		}

		public EncryptionUtil build() {
			return new EncryptionUtil(this);
		}
	}

}
