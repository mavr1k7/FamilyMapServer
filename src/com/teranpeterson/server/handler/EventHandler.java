package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler for Event(s) Requests. URL: /event/{eventID} or /event
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
