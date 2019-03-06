package com.teranpeterson.server.handler;

import com.google.gson.Gson;
import com.teranpeterson.server.request.*;

import java.io.Reader;

/**
 * Deserializer for json objects
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
class Deserializer {
    static RegisterRequest registerRequest(Reader reader) {
        return new Gson().fromJson(reader, RegisterRequest.class);
    }

    static LoginRequest loginRequest(Reader reader) {
        return new Gson().fromJson(reader, LoginRequest.class);
    }

    static FillRequest fillRequest(Reader reader) {
        return new Gson().fromJson(reader, FillRequest.class);
    }

    static LoadRequest loadRequest(Reader reader) {
        return new Gson().fromJson(reader, LoadRequest.class);
    }

    static PersonRequest personRequest(Reader reader) {
        return new Gson().fromJson(reader, PersonRequest.class);
    }

    static EventRequest eventRequest(Reader reader) {
        return new Gson().fromJson(reader, EventRequest.class);
    }
}
