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
        return new Gson().fromJson(reader, RegisterRequest.class);
    }

    public static LoginRequest loginRequest(Reader reader) {
        return new Gson().fromJson(reader, LoginRequest.class);
    }
}
