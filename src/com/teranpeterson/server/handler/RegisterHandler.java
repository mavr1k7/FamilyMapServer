package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.RegisterResult;
import com.teranpeterson.server.service.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handler for Register Requests. URL: /user/register
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Reader reader = new InputStreamReader(exchange.getRequestBody());
            RegisterRequest request = Deserializer.registerRequest(reader);
            RegisterResult result = new RegisterService().register(request);

            String response;
            if (result.getMessage() == null) {
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
