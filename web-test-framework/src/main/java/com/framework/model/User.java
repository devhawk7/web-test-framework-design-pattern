package com.framework.model;

import java.util.Objects;

public record User(String username, String password) {

    /** PATTERN (bonus): Builder. */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "User[username=" + username + ", password=****]";
    }

    public static final class Builder {
        private String username;
        private String password;

        public Builder username(String username) { this.username = username; return this; }
        public Builder password(String password) { this.password = password; return this; }

        public User build() {
            return new User(Objects.requireNonNull(username, "username is required"),
                            Objects.requireNonNull(password, "password is required"));
        }
    }
}
