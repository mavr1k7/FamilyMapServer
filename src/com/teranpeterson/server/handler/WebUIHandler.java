package com.teranpeterson.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.*;

/**
 * Handler for the web interface
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class WebUIHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String url = exchange.getRequestURI().toString();

        if (url.length() == 1) {
            url = "resources/web/index.html";
        }
        else {
            url = "resources/web" + url;
        }

        exchange.sendResponseHeaders(200, 0);
        Files.copy(Paths.get(url), exchange.getResponseBody());
        exchange.getResponseBody().close();
    }
}
