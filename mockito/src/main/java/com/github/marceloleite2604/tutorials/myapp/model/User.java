package com.github.marceloleite2604.tutorials.myapp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

    public User() {
    }

    private long id;

    private String username;

    private String email;

    private LocalDate dataOfBirth;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(LocalDate dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", dataOfBirth=" + dataOfBirth +
            '}';
    }

    public static final class Builder {
        private long id;
        private String username;
        private String email;
        private LocalDate dataOfBirth;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder dataOfBirth(LocalDate dataOfBirth) {
            this.dataOfBirth = dataOfBirth;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setEmail(email);
            user.setDataOfBirth(dataOfBirth);
            return user;
        }
    }
}
