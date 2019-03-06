package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.RegisterResult;
import com.teranpeterson.server.service.RegisterService;

import java.io.*;

/**
 * Handler for Register Requests. URL: /user/register
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        RegisterService service = new RegisterService();

        Reader reader = new InputStreamReader(exchange.getRequestBody());
        RegisterRequest request = Deserializer.registerRequest(reader);
        RegisterResult result = service.register(request);

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
