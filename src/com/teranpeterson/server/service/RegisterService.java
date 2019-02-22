package com.teranpeterson.server.service;

import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.RegisterResult;

/**
 * Creates a new user account, generates 4 generations of ancestor data for the new
 * user, logs the user in, and returns an auth token.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class RegisterService extends Service {
    /**
     * Creates a blank register service object
     */
    public RegisterService() {

    }

    /**
     * Creates a new user account, generates 4 generations of ancestor data for the new
     * user, logs the user in, and returns an auth token.
     *
     * @param request Information about the user registering
     * @return Information about the person created or an error
     */
    public RegisterResult register(RegisterRequest request) {
        return null;
    }
}
