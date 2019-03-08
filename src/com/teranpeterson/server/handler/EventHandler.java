package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.helpers.Serializer;
import com.teranpeterson.server.request.EventRequest;
import com.teranpeterson.server.result.EventResult;
import com.teranpeterson.server.service.EventService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handler for Event(s) Requests. URL: /event/{eventID} or /event
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            // Split URL request to find eventID
            String url = exchange.getRequestURI().toString().substring(6);
            String token = exchange.getRequestHeaders().getFirst("Authorization");
            String[] params = url.split("/");

            // If eventID is provided, return that event. Otherwise return ALL events
            boolean success = true;
            EventRequest request = null;
            if (params.length == 0 || params.length == 1) {
                request = new EventRequest(token);
            } else if (params.length == 2) {
                request = new EventRequest(params[1], token);
            } else {
                success = false;
            }

            // Run service and get result
            EventResult result;
            if (success) result = new EventService().event(request);
            else result = new EventResult("ERROR: Invalid parameters");

            // Build response
            String response;
            if (result.isSuccess()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                response = Serializer.serialize(result);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                response = "{\"message\" : \"" + result.getMessage() + "\"}";
            }

            OutputStream body = exchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(body);
            writer.write(response);
            writer.flush();
            body.close();
        } catch (IOException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            String response = "{\"message\" : \"ERROR: Internal server error\"}";
            OutputStream body = exchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(body);
            writer.write(response);
            writer.flush();
            body.close();
        }
    }
}
