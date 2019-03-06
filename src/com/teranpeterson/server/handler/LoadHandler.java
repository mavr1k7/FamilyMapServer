package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.teranpeterson.server.request.LoadRequest;
import com.teranpeterson.server.result.LoadResult;
import com.teranpeterson.server.service.LoadService;

import java.io.*;

/**
 * Handler for Load Requests. URL: /load
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoadService service = new LoadService();

        Reader reader = new InputStreamReader(exchange.getRequestBody());
        LoadRequest request = Deserializer.loadRequest(reader);
        LoadResult result = service.load(request);

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
