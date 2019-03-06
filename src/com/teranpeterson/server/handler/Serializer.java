package com.teranpeterson.server.handler;

import com.google.gson.Gson;
import com.teranpeterson.server.result.EventResult;
import com.teranpeterson.server.result.LoginResult;
import com.teranpeterson.server.result.PersonResult;
import com.teranpeterson.server.result.RegisterResult;

/**
 * Serializer for json objects
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class Serializer {
    public static String serialize(RegisterResult result) {
        return new Gson().toJson(result);
    }

    public static String serialize(LoginResult result) { return new Gson().toJson(result); }

    public static String serialize(PersonResult result) { return new Gson().toJson(result); }

    public static String serialize(EventResult result) { return new Gson().toJson(result); }
}
