package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler for Person(s) Requests. URL: /person/{personID} or /person
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class PersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
