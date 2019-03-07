package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.FillRequest;
import com.teranpeterson.server.result.FillResult;
import com.teranpeterson.server.service.FillService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handler for Fill Requests. URL: /fill/{username}/{generations}
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String url = exchange.getRequestURI().toString().substring(6);
            String[] params = url.split("/");

            boolean success = true;
            FillRequest request = null;
            if (params.length == 1) {
                request = new FillRequest(params[0]);
            } else if (params.length == 2) {
                try {
                    request = new FillRequest(params[0], Integer.parseInt(params[1]));
                } catch (NumberFormatException e) {
                    success = false;
                }
            } else {
                success = false;
            }

            FillResult result;
            if (success) result = new FillService().fill(request);
            else result = new FillResult("ERROR: Invalid parameters");

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
