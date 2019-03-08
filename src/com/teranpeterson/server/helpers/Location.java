package com.teranpeterson.server.helpers;

/**
 * Location object built from json file. Used to store latitude, longitude, city and country of an event
 */
public class Location {
    private String country;
    private String city;
    private double latitude;
    private double longitude;

    public Location() {
        country = "";
        city = "";
        latitude = 0.0;
        longitude = 0.0;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return city + ", " + country + ":" + latitude + "><" + longitude;
    }
}
