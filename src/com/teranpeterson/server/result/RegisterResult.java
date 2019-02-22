package com.teranpeterson.server.result;

/**
 * Contains information about the results of a Register Request
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class RegisterResult extends Result {
    /**
     * Authentication token for the active user session
     */
    private String authToken;
    /**
     * Username of registered user
     */
    private String username;
    /**
     * PersonID of person created for the user
     */
    private String personID;

    /**
     * Creates a failing register result with the given error message
     * @param message Description of the error
     */
    public RegisterResult(String message) {

    }

    /**
     * Creates a successful register result with users session information
     * @param token Authentication token for the active user session
     * @param username Username of registered user
     * @param id PersonID of person created for the user
     */
    public RegisterResult(String token, String username, String id) {

    }

    /**
     * Gets the users new authentication token
     * @return Active authentication token
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets a new authentication token for the user
     * @param authToken User's authentication token
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
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
     * Gets the personID that corresponds with the user
     * @return User's personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the personID that corresponds with the user
     * @param personID User's personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
