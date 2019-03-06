package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.result.LoginResult;
import com.teranpeterson.server.service.LoginService;

import java.io.*;

/**
 * Handler for Login Requests. URL: /user/login
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoginService service = new LoginService();

        Reader reader = new InputStreamReader(exchange.getRequestBody());
        LoginRequest request = Deserializer.loginRequest(reader);
        LoginResult result = service.login(request);

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
