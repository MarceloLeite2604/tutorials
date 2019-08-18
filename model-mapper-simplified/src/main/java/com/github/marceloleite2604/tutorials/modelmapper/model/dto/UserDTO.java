package com.github.marceloleite2604.tutorials.modelmapper.model.dto;

import javax.validation.constraints.NotBlank;

import com.github.marceloleite2604.tutorials.modelmapper.model.validation.HttpPostValidationGroup;

public class UserDTO implements DataTransferObject<String>, Comparable<UserDTO> {

	private static final long serialVersionUID = 1L;

	private String id;

	@NotBlank(groups = HttpPostValidationGroup.class, message = "{user.edit.username.validation.not-blank}")
	private String username;

	@NotBlank(groups = HttpPostValidationGroup.class, message = "{user.edit.name.validation.not-blank}")
	private String name;

	private String password;

	private boolean enabled;

	private boolean deleted;

	private UserDTO(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.name = builder.name;
		this.password = builder.password;
		this.enabled = builder.enabled;
		this.deleted = builder.deleted;
	}

	public UserDTO() {
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", name=" + name + ", password="
				+ password + ", enabled=" + enabled + ", deleted=" + deleted + "]";
	}

	public static Builder builder() {
		return new Builder();
	}
	
	@Override
	public int compareTo(UserDTO other) {
		return this.name.compareTo(other.getName());
	}

	public static final class Builder {
		private String id;
		private String username;
		private String name;
		private String password;
		private boolean enabled;
		private boolean deleted;

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder deleted(boolean deleted) {
			this.deleted = deleted;
			return this;
		}

		public UserDTO build() {
			return new UserDTO(this);
		}
	}
}
