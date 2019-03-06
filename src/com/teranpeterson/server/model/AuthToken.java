package com.teranpeterson.server.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Authentication Tokens are used to establish and maintain user sessions for security and convenience. Tokens are
 * randomly generated and expire after an hour of no use. Users can have multiple tokens simultaneously corresponding
 * to different clients.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class AuthToken {
    /**
     * UserName of the user who's token this is
     */
    private String userName;
    /**
     * Randomly generated unique string used for authentication
     */
    private String token;

    /**
     * Assigns a randomly generated token for the given user
     *
     * @param userName UserName of the user who is logging in
     */
    public AuthToken(String userName) {
        this.userName = userName;
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Creates a new AuthToken object with a given userName and token
     *
     * @param userName UserName of the user
     * @param token    String to act as authentication token
     */
    public AuthToken(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    /**
     * Gets the users token for authentication
     *
     * @return A valid authentication token to use in place of logging in
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the users authentication token
     *
     * @param token Randomly generated string used for authentication
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the user's userName
     *
     * @return User's userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's userName
     *
     * @param userName User's userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(userName, authToken.userName) &&
                Objects.equals(token, authToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, token);
    }
}
