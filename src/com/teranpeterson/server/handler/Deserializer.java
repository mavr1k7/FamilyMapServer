package com.teranpeterson.server.handler;

import com.google.gson.Gson;
import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.request.RegisterRequest;

import java.io.Reader;

/**
 * Deserializer for json objects
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class Deserializer {
    public static RegisterRequest registerRequest(Reader reader) {
        Gson gson = new Gson();
        RegisterRequest request = gson.fromJson(reader, RegisterRequest.class);
        System.out.println("3" + request.getFirstName());
        return request;
    }

    public static LoginRequest loginRequest(Reader reader) {
        LoginRequest out;
        Gson gson = new Gson();
        out = gson.fromJson(reader, LoginRequest.class);
        return out;
    }
}
