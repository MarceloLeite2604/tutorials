package com.github.marceloleite2604.tutorials.modelmapper.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.marceloleite2604.tutorials.modelmapper.model.validation.HttpPostValidationGroup;

public class GameDTO implements DataTransferObject {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(groups = HttpPostValidationGroup.class, message = "{game.edit.name.validation.not-blank}")
	private String name;

	@NotBlank(groups = HttpPostValidationGroup.class, message = "{game.edit.developer.validation.not-blank}")
	private String developer;

	@NotBlank(groups = HttpPostValidationGroup.class, message = "{game.edit.genre.validation.not-blank}")
	private String genre;

	@Min(value = 1980, groups = HttpPostValidationGroup.class, message = "{game.edit.release-year.validation.min}")
	@NotNull(groups = HttpPostValidationGroup.class, message = "{game.edit.release-year.validation.not-null}")
	private Integer releaseYear;

	@SuppressWarnings("squid:S2637")
	public GameDTO() {
	}

	private GameDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.developer = builder.developer;
		this.genre = builder.genre;
		this.releaseYear = builder.releaseYear;
	}

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
		GameDTO other = (GameDTO) obj;
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

		public GameDTO build() {
			return new GameDTO(this);
		}
	}
}
