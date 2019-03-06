package com.teranpeterson.server.model;

import java.util.Objects;

/**
 * Users are able to access the system and see their ancestors family history information. Each user has a unique
 * username, email and password that are used to login to the system. Passwords are stored hashed and salted in the
 * database for security. The users basic information is also stored. Each user has a corresponding Person object.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class User {
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
     * User's unique personID
     */
    private String personID;

    /**
     * Creates a blank user
     */
    public User() {

    }

    /**
     * Creates a new user with the given information and automatically generates a new person and personID
     *
     * @param username  User's username
     * @param password  User's password
     * @param email     User's email
     * @param firstname User's first name
     * @param lastname  User's last name
     * @param gender    User's gender ('m' or 'f')
     */
    public User(String username, String password, String email, String firstname, String lastname, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Gets the user's username
     *
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     *
     * @param username A new username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     *
     * @param password The user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email
     *
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email
     *
     * @param email A new email for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's first name
     *
     * @return The user's first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name
     *
     * @param firstname The user's first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the user's last name
     *
     * @return The user's last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the user's last name
     *
     * @param lastname The user's last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the user's gender
     *
     * @return (' m ' or ' f ')
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the user's gender
     *
     * @param gender ('m' or 'f')
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the user's personID
     *
     * @return The user's personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the user's personID
     *
     * @param personID User's personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(personID, user.personID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, firstname, lastname, gender, personID);
    }
}
