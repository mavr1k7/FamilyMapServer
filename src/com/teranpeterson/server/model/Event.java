package com.teranpeterson.server.model;

import java.util.Objects;

/**
 * Events store information about significant events from a persons life, ie. birth, marriage, death, etc. Each event
 * has a personID that corresponds to a person in the system. The latitude and longitude values are used by the client
 * to map these events.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class Event {
    /**
     * Unique id for the event
     */
    private String eventID;
    /**
     * Username of the user the event belongs to
     */
    private String descendant;
    /**
     * Unique id of the person the event happened to
     */
    private String personID;
    /**
     * Latitude where the event took place
     */
    private double latitude;
    /**
     * Longitude where the event took place
     */
    private double longitude;
    /**
     * Country where the event took place
     */
    private String country;
    /**
     * City where the event took place
     */
    private String city;
    /**
     * Type of event (birth, baptism, christening, marriage, death, or burial)
     */
    private String type;
    /**
     * Year the event took place
     */
    private int year;

    /**
     * Creates a blank event
     */
    public Event() {

    }

    /**
     * Creates an event with the given data and automatically assigns an eventID
     *
     * @param descendant Username of the user this event belongs to
     * @param personID   ID of the person the event happened to
     * @param latitude   Latitude where the event took place
     * @param longitude  Longitude where the event took place
     * @param country    Country where the event took place
     * @param city       City where the event took place
     * @param type       Type of event (birth, baptism, christening, marriage, death, or burial)
     * @param year       Year the event took place
     */
    public Event(String eventID, String descendant, String personID, double latitude, double longitude, String country, String city, String type, int year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.type = type;
        this.year = year;
    }

    /**
     * Gets the eventID
     *
     * @return The event's ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets a new eventID
     *
     * @param eventID Unique id for the event
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * Gets the descendant's username
     *
     * @return The descendant's username
     */
    public String getDescendant() {
        return descendant;
    }

    /**
     * Sets a new descendant
     *
     * @param descendant Username of the descendant
     */
    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    /**
     * Gets the personID of the person this even happened to
     *
     * @return A personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the person that the event 'happened' to
     *
     * @param personID PersonID of person
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Gets the latitude where the event took place
     *
     * @return A floating point number for latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude where the event took place
     *
     * @param latitude Floating point number for latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude where the event took place
     *
     * @return A floating point number for longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude where the event took place
     *
     * @param longitude Floating point number for longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the country where the event took place
     *
     * @return Country name where the event took place
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country where the event took place
     *
     * @param country Country name where the event took place
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city where the event took place
     *
     * @return City where the event took place
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the event took place
     *
     * @param city City where the event took place
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the ype of event
     *
     * @return birth, baptism, christening, marriage, death, or burial
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of event
     *
     * @param type birth, baptism, christening, marriage, death, or burial
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the year the event took place
     *
     * @return Year the event took place
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year the event took place
     *
     * @param year Year the event took place
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Double.compare(event.latitude, latitude) == 0 &&
                Double.compare(event.longitude, longitude) == 0 &&
                year == event.year &&
                Objects.equals(eventID, event.eventID) &&
                Objects.equals(descendant, event.descendant) &&
                Objects.equals(personID, event.personID) &&
                Objects.equals(country, event.country) &&
                Objects.equals(city, event.city) &&
                Objects.equals(type, event.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, descendant, personID, latitude, longitude, country, city, type, year);
    }
}
