package com.github.marceloleite2604.tutorials.modelmapper.model.po;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "libraries")
@Table(name = "libraries")
public class LibraryPO implements PersistentObject<UUID> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-binary")
	private UUID id;

	@ManyToOne
	private UserPO user;

	@ManyToOne
	private GamePO game;

	@Column(nullable = false)
	private Double hoursPlayed;
	
	private LibraryPO() {
		// Private constructor to avoid object instantiation.
	}

	private LibraryPO(Builder builder) {
		this.id = builder.id;
		this.user = builder.user;
		this.game = builder.game;
		this.hoursPlayed = builder.hoursPlayed;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	public GamePO getGame() {
		return game;
	}

	public void setGame(GamePO game) {
		this.game = game;
	}

	public Double getHoursPlayed() {
		return hoursPlayed;
	}

	public void setHoursPlayed(Double hoursPlayed) {
		this.hoursPlayed = hoursPlayed;
	}

	public static Builder builder() {
		return new Builder();
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
		LibraryPO other = (LibraryPO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LibraryPO [id=" + id + ", user=" + user + ", game=" + game + ", hoursPlayed="
				+ hoursPlayed + "]";
	}

	public static final class Builder {
		private UUID id;
		private UserPO user;
		private GamePO game;
		private Double hoursPlayed;

		private Builder() {
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder user(UserPO user) {
			this.user = user;
			return this;
		}

		public Builder game(GamePO game) {
			this.game = game;
			return this;
		}

		public Builder hoursPlayed(Double hoursPlayed) {
			this.hoursPlayed = hoursPlayed;
			return this;
		}

		public LibraryPO build() {
			return new LibraryPO(this);
		}
	}
}
