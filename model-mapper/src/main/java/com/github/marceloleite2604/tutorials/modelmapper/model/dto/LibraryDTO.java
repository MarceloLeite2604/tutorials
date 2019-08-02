package com.github.marceloleite2604.tutorials.modelmapper.model.dto;

public class LibraryDTO implements DataTransferObject {

	private static final long serialVersionUID = 1L;

	private String id;

	private UserDTO user;

	private GameDTO game;

	private Double hoursPlayed;

	private LibraryDTO(Builder builder) {
		this.id = builder.id;
		this.user = builder.user;
		this.game = builder.game;
		this.hoursPlayed = builder.hoursPlayed;
	}

	public LibraryDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public GameDTO getGame() {
		return game;
	}

	public void setGame(GameDTO game) {
		this.game = game;
	}

	public Double getHoursPlayed() {
		return hoursPlayed;
	}

	public void setHoursPlayed(Double hoursPlayed) {
		this.hoursPlayed = hoursPlayed;
	}

	@Override
	public String toString() {
		return "LibraryDTO [id=" + id + ", user=" + user + ", game=" + game + ", hoursPlayed="
				+ hoursPlayed + "]";
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
		LibraryDTO other = (LibraryDTO) obj;
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
		private String id;
		private UserDTO user;
		private GameDTO game;
		private Double hoursPlayed;

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder user(UserDTO user) {
			this.user = user;
			return this;
		}

		public Builder game(GameDTO game) {
			this.game = game;
			return this;
		}

		public Builder hoursPlayed(Double hoursPlayed) {
			this.hoursPlayed = hoursPlayed;
			return this;
		}

		public LibraryDTO build() {
			return new LibraryDTO(this);
		}
	}
}
