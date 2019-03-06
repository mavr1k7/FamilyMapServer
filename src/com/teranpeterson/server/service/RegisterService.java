package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.*;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

/**
 * Creates a new user account, generates 4 generations of ancestor data for the new
 * user, logs the user in, and returns an auth token.
 *
 * @author Teran Peterson
 * @version v0.1.1
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
        String id = UUID.randomUUID().toString().substring(0, 6);
        User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstname(), request.getLastname(), request.getGender(), id);
        Database db = new Database();
        try {
            db.createTables();
            Connection conn = db.openConnection();
            super.generate(conn, newUser, 4);
            UserDAO uDAO = new UserDAO(conn);
            uDAO.insert(newUser);
            String token = super.login(conn, newUser.getUsername());
            db.closeConnection(true);
            return new RegisterResult(token, newUser.getUsername(), id);
        } catch (DAOException e) {
            try {
                db.closeConnection(false);
                return new RegisterResult(e.getMessage());
            } catch (DAOException d) {
                return new RegisterResult(d.getMessage());
            }
        }
    }
}
