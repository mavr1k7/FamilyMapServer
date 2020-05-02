package com.teranpeterson.server.helpers;

import com.google.gson.Gson;
import com.teranpeterson.server.request.*;

import java.io.Reader;

/**
 * Deserializer for json objects
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class Deserializer {
    public static RegisterRequest registerRequest(Reader reader) { return new Gson().fromJson(reader, RegisterRequest.class); }

    public static LoginRequest loginRequest(Reader reader) {
        return new Gson().fromJson(reader, LoginRequest.class);
    }

    public static LoadRequest loadRequest(Reader reader) {
        return new Gson().fromJson(reader, LoadRequest.class);
    }
}
