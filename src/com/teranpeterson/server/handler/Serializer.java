package com.teranpeterson.server.handler;

import com.google.gson.Gson;
import com.teranpeterson.server.result.RegisterResult;

/**
 * Serializer for json objects
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class Serializer {
    public static String serialize(RegisterResult r) {
        return new Gson().toJson(r);
    }
}
