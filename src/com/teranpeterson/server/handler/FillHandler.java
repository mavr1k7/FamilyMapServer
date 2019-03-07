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
 * @version v0.1.2
 */
public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        FillService service = new FillService();

        String url = exchange.getRequestURI().toString();
        String[] params = url.split("/");

        if(exchange.getRequestHeaders().containsKey("Authorization")) {
            String authToken = exchange.getRequestHeaders().getFirst("Authorization");
            exchange.getRequestMethod().toLowerCase().equals("get");
        }

        FillRequest request = null;

        if (params.length == 3) {
            request = new FillRequest(params[2]);
        }
        else if (params.length == 4) {
            try {
                int n = Integer.parseInt(params[3]);
                request = new FillRequest(params[2], n);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Invalid params");
        }

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
