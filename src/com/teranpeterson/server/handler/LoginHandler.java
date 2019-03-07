package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.result.LoginResult;
import com.teranpeterson.server.service.LoginService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handler for Login Requests. URL: /user/login
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Reader reader = new InputStreamReader(exchange.getRequestBody());
            LoginRequest request = Deserializer.loginRequest(reader);
            LoginResult result = new LoginService().login(request);

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
