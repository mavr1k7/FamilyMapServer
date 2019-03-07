package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.EventRequest;
import com.teranpeterson.server.result.EventResult;
import com.teranpeterson.server.service.EventService;

import java.io.*;

/**
 * Handler for Event(s) Requests. URL: /event/{eventID} or /event
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        EventService service = new EventService();

        String url = exchange.getRequestURI().toString();
        String[] params = url.split("/");

        EventRequest request = null;

        if (params.length == 2) {
            request = new EventRequest("token");
        }
        else if (params.length == 3) {
            request = new EventRequest(params[2], "token");
        }
        else {
            System.out.println("Invalid params");
        }

        EventResult result = service.event(request);

        if (result.isSuccess()) {
            exchange.sendResponseHeaders(200, 0);
            String response = Serializer.serialize(result);
            OutputStream body = exchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(body);
            writer.write(response);
            writer.flush();
            body.close();
        }
        else {
            exchange.sendResponseHeaders(400, 0);
            String response = "{\"message\" : \"" + result.getMessage() + "\"}";
            OutputStream body = exchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(body);
            writer.write(response);
            writer.flush();
            body.close();
        }
    }
}
