package com.teranpeterson.server.request;

/**
 * Contains information about the Login Request
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoginRequest extends Request {
    /**
     * User's username
     */
    private String username;
    /**
     * Password to authenticate with
     */
    private String password;

    /**
     * Creates a login request with the given information
     * @param username User's username
     * @param password Password to authenticate with
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user's username
     * @return User's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     * @param username User's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password to authenticate with
     * @return Password to authenticate with
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to authenticate with
     * @param password Password to authenticate with
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
