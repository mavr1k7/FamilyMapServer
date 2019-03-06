package com.teranpeterson.server.result;

import com.teranpeterson.server.model.Person;

import java.util.ArrayList;

/**
 * Contains information about the results of a Person(s) Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class PersonResult extends Result {
    /**
     * List of all the people to return
     */
    private ArrayList<Person> persons;
    /**
     * Person to return
     */
    private Person person;

    /**
     * Creates a successful person result with information about ALL the persons
     *
     * @param persons Stores persons
     */
    public PersonResult(ArrayList<Person> persons) {
        this.persons = persons;
        super.success = true;
    }

    /**
     * Creates a successful person result with information about the person
     *
     * @param person Stores person
     */
    public PersonResult(Person person) {
        this.person = person;
        super.success = true;
    }

    /**
     * Creates a failing person result with the given error message
     *
     * @param message Description of the error
     */
    public PersonResult(String message) {
        super.message = message;
        super.success = false;
    }

    /**
     * Gets a list of all the persons related to the user
     *
     * @return List of all the persons related to the user
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Sets a list of all the persons related to the user
     *
     * @param persons List of all the persons related to the user
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * Adds a person to the list of persons related to the user
     *
     * @param person Person related to the user
     */
    public void addPerson(Person person) {
        this.persons.add(person);
    }

    /**
     * Gets the person related to the user
     *
     * @return Person related to the user
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person related to the user
     *
     * @param person Person related to the user
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
