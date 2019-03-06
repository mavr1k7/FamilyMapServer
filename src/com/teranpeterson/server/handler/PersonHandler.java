package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.PersonRequest;
import com.teranpeterson.server.result.PersonResult;
import com.teranpeterson.server.service.PersonService;

import java.io.*;

/**
 * Handler for Person(s) Requests. URL: /person/{personID} or /person
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class PersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PersonService service = new PersonService();

        Reader reader = new InputStreamReader(exchange.getRequestBody());
        PersonRequest request = Deserializer.personRequest(reader);
        PersonResult result = service.person(request);

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
