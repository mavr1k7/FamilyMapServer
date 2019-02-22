package com.teranpeterson.server.request;

import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;

import java.util.ArrayList;

/**
 * Contains information about the Load Request
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoadRequest extends Request {
    /**
     * List of users to load into the database
     */
    private ArrayList<User> users;
    /**
     * List of persons to load into the database
     */
    private ArrayList<Person> persons;
    /**
     * List of events to load into the database
     */
    private ArrayList<Event> events;

    /**
     * Creates a load request with the given information
     * @param users List of users to load into the database
     * @param persons List of persons to load into the database
     * @param events List of events to load into the database
     */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Gets the list of users to load into the database
     * @return List of users to load into the database
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users to load into the database
     * @param users List of users to load into the database
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Gets the list of persons to load into the database
     * @return List of persons to load into the database
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Sets the list of persons to load into the database
     * @param persons List of persons to load into the database
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * Gets the list of events to load into the database
     * @return List of events to load into the database
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Sets the list of events to load into the database
     * @param events List of events to load into the database
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
