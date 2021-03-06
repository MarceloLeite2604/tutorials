package com.github.marceloleite2604.tutorials.modelmapper.model.po;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.github.marceloleite2604.tutorials.modelmapper.model.po.converter.YesNoAttributeConverter;

@Entity(name = "users")
@Table(name = "users")
public class UserPO implements PersistentObject<UUID>, Comparable<UserPO> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-binary")
	private UUID id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Convert(converter = YesNoAttributeConverter.class)
	private Boolean enabled;

	@Column(nullable = false)
	@Convert(converter = YesNoAttributeConverter.class)
	private Boolean deleted;

	private UserPO() {
		// Private constructor to avoid object instantiation.
	}

	private UserPO(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.name = builder.name;
		this.password = builder.password;
		this.enabled = builder.enabled;
		this.deleted = builder.deleted;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "UserPO [id=" + id + ", username=" + username + ", name=" + name + ", password="
				+ password + ", enabled=" + enabled + ", deleted=" + deleted + "]";
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
		UserPO other = (UserPO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private UUID id;
		private String username;
		private String name;
		private String password;
		private Boolean enabled;
		private Boolean deleted;

		private Builder() {
		}

		public Builder id(UUID id) {
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

		public Builder enabled(Boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder deleted(Boolean deleted) {
			this.deleted = deleted;
			return this;
		}

		public UserPO build() {
			return new UserPO(this);
		}
	}

	@Override
	public int compareTo(UserPO other) {
		return this.name.compareTo(other.getName());
	}
}
