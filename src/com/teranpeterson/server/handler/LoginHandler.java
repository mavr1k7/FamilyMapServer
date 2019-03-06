package com.teranpeterson.server.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.LoginRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 * Handler for Login Requests. URL: /user/login
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Called login handler");

        Reader reader = new InputStreamReader(exchange.getRequestBody());
        Gson gson = new Gson();
        LoginRequest request = gson.fromJson(reader, LoginRequest.class);
        System.out.println(request.getPassword());
    }
}
