package io.github.marceloleite2604.model.dto;

import io.github.marceloleite2604.model.validation.HttpPostValidationGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

public class PersonDto {

    private String id;

    @NotBlank(groups = HttpPostValidationGroup.class, message = "{validation.person-dto.name.not-blank}")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto person = (PersonDto) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class Builder {
        private String id;
        private String name;

        private Builder() {
        }

        public static Builder aPerson() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public PersonDto build() {
            PersonDto person = new PersonDto();
            person.setId(id);
            person.setName(name);
            return person;
        }
    }
}
