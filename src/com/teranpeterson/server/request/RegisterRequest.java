package com.teranpeterson.server.request;

/**
 * Contains information about the Register Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class RegisterRequest {
    /**
     * User's username
     */
    private String username;
    /**
     * User's password
     */
    private String password;
    /**
     * User's email
     */
    private String email;
    /**
     * User's first name
     */
    private String firstname;
    /**
     * User's last name
     */
    private String lastname;
    /**
     * User's gender ('m' or 'f')
     */
    private String gender;

    /**
     * Creates a register request with the given information
     *
     * @param username  User's username
     * @param password  User's password
     * @param email     User's email
     * @param firstname User's first name
     * @param lastname  User's last name
     * @param gender    User's gender
     */
    public RegisterRequest(String username, String password, String email, String firstname, String lastname, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    /**
     * Gets the user's username
     *
     * @return User's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     *
     * @param username User's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password to authenticate with
     *
     * @return Password to authenticate with
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to authenticate with
     *
     * @param password Password to authenticate with
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email
     *
     * @return User's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email
     *
     * @param email User's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's first name
     *
     * @return User's first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name
     *
     * @param firstname User's first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the user's last name
     *
     * @return User's last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the user's last name
     *
     * @param lastname User's last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the user's gender ('m' or 'f')
     *
     * @return User's gender ('m' or 'f')
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the user's gender ('m' or 'f')
     *
     * @param gender User's gender ('m' or 'f')
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
