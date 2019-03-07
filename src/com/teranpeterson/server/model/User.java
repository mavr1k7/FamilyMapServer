package com.teranpeterson.server.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Users are able to access the system and see their ancestors family history information. Each user has a unique
 * userName, email and password that are used to login to the system. Passwords are stored hashed and salted in the
 * database for security. The users basic information is also stored. Each user has a corresponding Person object.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class User {
    /**
     * User's userName
     */
    private String userName;
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
    private String firstName;
    /**
     * User's last name
     */
    private String lastName;
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
     * Creates a new user with the given information
     *
     * @param userName  User's userName
     * @param password  User's password
     * @param email     User's email
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param gender    User's gender ('m' or 'f')
     * @param personID  User's person id
     */
    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Creates a new user with the given information and automatically generates a personID
     *
     * @param userName  User's userName
     * @param password  User's password
     * @param email     User's email
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param gender    User's gender ('m' or 'f')
     */
    public User(String userName, String password, String email, String firstName, String lastName, String gender) {
        this(userName, password, email, firstName, lastName, gender, UUID.randomUUID().toString().substring(0, 6));
    }

    /**
     * Gets the user's userName
     *
     * @return The user's userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's userName
     *
     * @param userName A new userName for the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     *
     * @param firstName The user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name
     *
     * @return The user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name
     *
     * @param lastName The user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(personID, user.personID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, email, firstName, lastName, gender, personID);
    }
}
