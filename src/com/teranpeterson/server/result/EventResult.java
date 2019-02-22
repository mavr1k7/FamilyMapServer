package com.teranpeterson.server.result;

import com.teranpeterson.server.model.Event;
import java.util.ArrayList;

/**
 * Contains information about the results of an Event(s) Request
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class EventResult extends Result {
    /**
     * List of all the events to return
     */
    private ArrayList<Event> events;
    /**
     * Event to return
     */
    private Event event;

    /**
     * Creates a successful event result with information about ALL the events
     * @param events
     */
    public EventResult(ArrayList<Event> events) {

    }

    /**
     * Creates a successful event result with information about the event
     * @param event
     */
    public EventResult(Event event) {

    }

    /**
     * Creates a failing event result with the given error message
     * @param message Description of the error
     */
    public EventResult(String message) {

    }

    /**
     * Gets a list of all the events related to the user
     * @return List of all the events related to the user
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Sets a list of all the events related to the user
     * @param events List of all the events related to the user
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Adds a event to the list of events related to the user
     * @param event Event related to the user
     */
    public void addEvent(Event event) {

    }

    /**
     * Gets the event related to the user
     * @return Event related to the user
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event related to the user
     * @param event Event related to the user
     */
    public void setEvent(Event event) {
        this.event = event;
    }
}
