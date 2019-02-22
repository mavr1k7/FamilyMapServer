package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Event;
import java.util.ArrayList;

/**
 * Controller used to connect to and modify events in the database
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class EventDAO {
    /**
     * Creates an empty event dao
     */
    public EventDAO() {

    }

    /**
     * Add an event to the database
     * @param event Event to add to the database
     */
    public void insert(Event event) {

    }

    /**
     * Find an event in the database
     * @param eventID ID of the event to find
     * @return If the event is found, return it, otherwise return null
     */
    public Event find(String eventID) {
        return null;
    }

    /**
     * Return a list of all events related to a person
     * @param personID ID of the person to find events for
     * @return List of all events related to person, or null if none are found
     */
    public ArrayList<Event> personEvents(String personID) {
        return null;
    }

    /**
     * Delete all events associated with a user
     * @param username User to delete events for
     */
    public void deleteEvents(String username) {

    }

    /**
     * Delete all events from database
     */
    public void clear() {

    }
}
