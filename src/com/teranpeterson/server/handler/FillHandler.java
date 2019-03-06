package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.FillRequest;
import com.teranpeterson.server.result.FillResult;
import com.teranpeterson.server.service.FillService;

import java.io.*;

/**
 * Handler for Fill Requests. URL: /fill/{username}/{generations}
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        FillService service = new FillService();

        Reader reader = new InputStreamReader(exchange.getRequestBody());
        FillRequest request = Deserializer.fillRequest(reader);
        FillResult result = service.fill(request);

        if (result.isSuccess()) {
            exchange.sendResponseHeaders(200, 0);
            String response = "{\"message\" : \"" + result.getMessage() + "\"}";
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
