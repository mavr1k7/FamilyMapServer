package com.teranpeterson.server.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Persons are relatives and ancestors to users in the system. Each person contains information about their relationships
 * with other persons. The unique personID is also used to connect specific events to each individual. Persons (and events)
 * are not based on real data but are instead randomly generated.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class Person {
    /**
     * Unique id for the person
     */
    private String personID;
    /**
     * UserName of the user the person belongs to (can be null)
     */
    private String descendant;
    /**
     * First name of the person
     */
    private String firstName;
    /**
     * Last name of the person
     */
    private String lastName;
    /**
     * Gender of the person ('m' or 'f')
     */
    private String gender;
    /**
     * Father of the person (can be null)
     */
    private String father;
    /**
     * Mother of the person (can be null)
     */
    private String mother;
    /**
     * Spouse of the person (can be null)
     */
    private String spouse;

    /**
     * Creates a blank person
     */
    public Person() {

    }

    /**
     * Creates a person with the given data
     *
     * @param personID   Person's id
     * @param descendant UserName of the user the person belongs to (default null)
     * @param firstName  First name of the person
     * @param lastName   Last name of the person
     * @param gender     Gender of the person ('m' or 'f')
     * @param father     Father of the person (default null)
     * @param mother     Mother of the person (default null)
     * @param spouse     Spouse of the person (default null)
     */
    public Person(String personID, String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    /**
     * Creates a person with the given data and automatically generates an id
     *
     * @param descendant UserName of the user the person belongs to (default null)
     * @param firstName  First name of the person
     * @param lastName   Last name of the person
     * @param gender     Gender of the person ('m' or 'f')
     * @param father     Father of the person (default null)
     * @param mother     Mother of the person (default null)
     * @param spouse     Spouse of the person (default null)
     */
    public Person(String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        this(UUID.randomUUID().toString().substring(0, 6), descendant, firstName, lastName, gender, father, mother, spouse);
    }

    /**
     * Creates a person with the given data and sets relatives all to null
     *
     * @param personID   Person's id
     * @param firstName  First name of the person
     * @param lastName   Last name of the person
     * @param gender     Gender of the person ('m' or 'f')
     */
    public Person(String personID, String firstName, String lastName, String gender) {
        this(personID, null, firstName, lastName, gender, null, null, null);
    }

    /**
     * Creates a person with the given data and sets relatives all to null
     *
     * @param personID   Person's id
     * @param firstName  First name of the person
     * @param lastName   Last name of the person
     * @param gender     Gender of the person ('m' or 'f')
     */
    public Person(String personID, String descendant, String firstName, String lastName, String gender) {
        this(personID, descendant, firstName, lastName, gender, null, null, null);
    }

    /**
     * Creates a person with the given data, sets relatives all to null and automatically generates an id
     *
     * @param firstName  First name of the person
     * @param lastName   Last name of the person
     * @param gender     Gender of the person ('m' or 'f')
     */
    public Person(String personID, String descendant, String firstName, String lastName, String gender, String spouse) {
        this(personID, descendant, firstName, lastName, gender, null, null, spouse);
    }

    /**
     * Gets the personID
     *
     * @return The person's id
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets a new personID
     *
     * @param personID Unique id for the person
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Gets the descendant's userName
     *
     * @return The descendant's userName
     */
    public String getDescendant() {
        return descendant;
    }

    /**
     * Sets a new descendant
     *
     * @param descendant UserName of the descendant
     */
    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    /**
     * Gets the person's first name
     *
     * @return The person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the person's first name
     *
     * @param firstName The person's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the person's last name
     *
     * @return The person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the person's last name
     *
     * @param lastName The person's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the person's gender
     *
     * @return 'm' or 'f'
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the person's gender
     *
     * @param gender 'm' or 'f'
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the personID for the person's father
     *
     * @return Father's personID
     */
    public String getFather() {
        return father;
    }

    /**
     * Sets the personID for the person's father
     *
     * @param father Father's personID
     */
    public void setFather(String father) {
        this.father = father;
    }

    /**
     * Gets the personID for the person's mother
     *
     * @return Mother's personID
     */
    public String getMother() {
        return mother;
    }

    /**
     * Sets the personID for the person's mother
     *
     * @param mother Mother's personID
     */
    public void setMother(String mother) {
        this.mother = mother;
    }

    /**
     * Gets the personID for the person's spouse
     *
     * @return Spouse's personID
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * Sets the personID for the person's spouse
     *
     * @param spouse Spouse's personID
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) &&
                Objects.equals(descendant, person.descendant) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(father, person.father) &&
                Objects.equals(mother, person.mother) &&
                Objects.equals(spouse, person.spouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID, descendant, firstName, lastName, gender, father, mother, spouse);
    }
}
