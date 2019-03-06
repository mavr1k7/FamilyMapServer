package com.teranpeterson.server.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.LoginRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Handler for Login Requests. URL: /user/login
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Reader reader = new InputStreamReader(exchange.getRequestBody());
        LoginRequest request = Deserializer.loginRequest(reader);
        System.out.println(request.getUserName());
    }
}
