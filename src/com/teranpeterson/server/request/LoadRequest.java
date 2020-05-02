package com.teranpeterson.server.request;

import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;

import java.util.List;

/**
 * Contains information about the Load Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class LoadRequest {
    /**
     * List of users to load into the database
     */
    private List<User> users;
    /**
     * List of persons to load into the database
     */
    private List<Person> persons;
    /**
     * List of events to load into the database
     */
    private List<Event> events;

    /**
     * Creates a load request with the given information
     *
     * @param users   List of users to load into the database
     * @param persons List of persons to load into the database
     * @param events  List of events to load into the database
     */
    public LoadRequest(List<User> users, List<Person> persons, List<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Gets the list of users to load into the database
     *
     * @return List of users to load into the database
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users to load into the database
     *
     * @param users List of users to load into the database
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Gets the list of persons to load into the database
     *
     * @return List of persons to load into the database
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Sets the list of persons to load into the database
     *
     * @param persons List of persons to load into the database
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Gets the list of events to load into the database
     *
     * @return List of events to load into the database
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets the list of events to load into the database
     *
     * @param events List of events to load into the database
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
