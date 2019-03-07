package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.LoadRequest;
import com.teranpeterson.server.result.LoadResult;
import com.teranpeterson.server.service.LoadService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handler for Load Requests. URL: /load
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Reader reader = new InputStreamReader(exchange.getRequestBody());
            LoadRequest request = Deserializer.loadRequest(reader);
            LoadResult result = new LoadService().load(request);

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
