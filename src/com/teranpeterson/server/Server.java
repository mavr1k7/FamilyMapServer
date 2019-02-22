package com.teranpeterson.server;

/**
 * Family Map Server. Used as the backend for the Family Map application. Accepts certain url requests and manages
 * users, persons, events, and authentication. A simple front end UI provides testing for the server.
 *
 * Accepted URLs:
 * /user/register
 * /user/login
 * /clear
 * /fill/{username}/{generations}
 * /load
 * /person/{personID}
 * /person
 * /event/{eventID}
 * /event
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class Server {
    /**
     * Creates a server instance that runs in the background and handles requests from the client
     * @param args Port number
     */
    public static void main(String[] args) {

    }

    /**
     * Creates a server object to handle incoming requests
     */
    public Server() {

    }
}
