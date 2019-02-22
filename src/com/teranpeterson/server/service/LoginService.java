package com.teranpeterson.server.service;

import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.result.LoginResult;

/**
 * Logs in the user and returns an auth token.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoginService extends Service {
    /**
     * Creates a blank login service object
     */
    public LoginService() {

    }

    /**
     * Logs in the user and returns an auth token.
     *
     * @param request Information about the user logging in
     * @return Information the user with an auth token for the active session
     */
    public LoginResult login(LoginRequest request) {
        return null;
    }
}
