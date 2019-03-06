package com.teranpeterson.server.result;

import com.teranpeterson.server.model.Event;

import java.util.List;

/**
 * Contains information about the results of an Event(s) Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class EventResult extends Result {
    /**
     * List of all the events to return
     */
    private List<Event> events;
    /**
     * Event to return
     */
    private Event event;

    /**
     * Creates a successful event result with information about ALL the events
     *
     * @param events Stores the events
     */
    public EventResult(List<Event> events) {
        this.events = events;
        super.success = true;
    }

    /**
     * Creates a successful event result with information about the event
     *
     * @param event Stores the event
     */
    public EventResult(Event event) {
        this.event = event;
        super.success = true;
    }

    /**
     * Creates a failing event result with the given error message
     *
     * @param message Description of the error
     */
    public EventResult(String message) {
        super.message = message;
        super.success = false;
    }

    /**
     * Gets a list of all the events related to the user
     *
     * @return List of all the events related to the user
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets a list of all the events related to the user
     *
     * @param events List of all the events related to the user
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Adds a event to the list of events related to the user
     *
     * @param event Event related to the user
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Gets the event related to the user
     *
     * @return Event related to the user
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event related to the user
     *
     * @param event Event related to the user
     */
    public void setEvent(Event event) {
        this.event = event;
    }
}
