package com.teranpeterson.server.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Authentication Tokens are used to establish and maintain user sessions for security and convenience. Tokens are
 * randomly generated and expire after an hour of no use. Users can have multiple tokens simultaneously corresponding
 * to different clients.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class AuthToken {
    /**
     * Username of the user who's token this is
     */
    private String username;
    /**
     * Randomly generated unique string used for authentication
     */
    private String token;

    /**
     * Assigns a randomly generated token for the given user
     * @param username Username of the user who is logging in
     */
    public AuthToken(String username) {
        this.username = username;
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Creates a new AuthToken object with a given username and token
     * @param username Username of the user
     * @param token String to act as authentication token
     */
    public AuthToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    /**
     * Gets the users token for authentication
     * @return A valid authentication token to use in place of logging in
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the users authentication token
     * @param token Randomly generated string used for authentication
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(username, authToken.username) &&
                Objects.equals(token, authToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, token);
    }
}
