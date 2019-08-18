package com.github.marceloleite2604.tutorials.modelmapper.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "games")
@Table(name = "games")
public class GamePO implements PersistentObject<Integer>, Comparable<GamePO> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String developer;

	@Column(nullable = false)
	private String genre;

	@Column(nullable = false)
	private Integer releaseYear;

	private GamePO() {
		// Private constructor to avoid object instantiation.
	}

	private GamePO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.developer = builder.developer;
		this.genre = builder.genre;
		this.releaseYear = builder.releaseYear;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
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
		GamePO other = (GamePO) obj;
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
		return "GamePO [id=" + id + ", name=" + name + ", developer=" + developer + ", genre="
				+ genre + ", releaseYear=" + releaseYear + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Integer id;
		private String name;
		private String developer;
		private String genre;
		private Integer releaseYear;

		private Builder() {
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder developer(String developer) {
			this.developer = developer;
			return this;
		}

		public Builder genre(String genre) {
			this.genre = genre;
			return this;
		}

		public Builder releaseYear(Integer releaseYear) {
			this.releaseYear = releaseYear;
			return this;
		}

		public GamePO build() {
			return new GamePO(this);
		}
	}

	@Override
	public int compareTo(GamePO other) {
		return this.name.compareTo(other.getName());
	}
}
