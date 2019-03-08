package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.result.ClearResult;
import com.teranpeterson.server.service.ClearService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handler for Clear Requests. URL: /clear
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            ClearResult result = new ClearService().clear();

            // Build response
            if (result.isSuccess()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }

            String response = "{\"message\" : \"" + result.getMessage() + "\"}";
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
