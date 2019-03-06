package com.teranpeterson.server;

import com.sun.net.httpserver.HttpServer;
import com.teranpeterson.server.handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;

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
    private HttpServer server;
    /**
     * Creates a server instance that runs in the background and handles requests from the client
     * @param args Port number
     */
    public static void main(String[] args) {
        int port = -1;
        try {
            port = Integer.parseInt(args[0]);
            if (port < 0 || port > 65535) System.out.println("ERROR: Invalid port number");
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid port number");
        }
        new Server().run(port);
    }

    /**
     * Creates a server object to handle incoming requests
     */
    public Server() {

    }

    public void run(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.setExecutor(null);
        server.createContext("/", new WebUIHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill/", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person/", new PersonHandler());
        server.createContext("/event/", new EventHandler());
        server.start();
    }
}
