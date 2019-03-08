package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.*;
import com.teranpeterson.server.helpers.Generator;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.LoginResult;

import java.sql.Connection;

/**
 * Creates a new user account, generates 4 generations of ancestor data for the new
 * user, logs the user in, and returns an auth token.
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class RegisterService {
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
    public LoginResult register(RegisterRequest request) {
        if (request.getUserName() == null || request.getUserName().isEmpty())
            return new LoginResult("ERROR: Missing userName parameter");
        if (request.getPassword() == null || request.getPassword().isEmpty())
            return new LoginResult("ERROR: Missing password parameter");
        if (request.getEmail() == null || request.getEmail().isEmpty())
            return new LoginResult("ERROR: Missing email parameter");
        if (request.getFirstName() == null || request.getFirstName().isEmpty())
            return new LoginResult("ERROR: Missing firstName parameter");
        if (request.getLastName() == null || request.getLastName().isEmpty())
            return new LoginResult("ERROR: Missing lastName parameter");
        if (request.getGender() == null || request.getGender().isEmpty())
            return new LoginResult("ERROR: Missing gender parameter");
        if (!request.getGender().equals("m") && !request.getGender().equals("f"))
            return new LoginResult("ERROR: Invalid gender parameter");
        User newUser = new User(request.getUserName(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender());

        Database db = new Database();
        try {
            db.createTables();
            Connection conn = db.openConnection();
            UserDAO uDAO = new UserDAO(conn);

            if (!uDAO.check(newUser.getUserName())) {
                try {
                    db.closeConnection(false);
                    return new LoginResult("ERROR: Username is already in use");
                } catch (DAOException e) {
                    e.printStackTrace();
                    return new LoginResult(e.getMessage());
                }
            }

            uDAO.insert(newUser);
            PersonDAO pDAO = new PersonDAO(conn);
            Person newPerson = new Person(newUser.getPersonID(), newUser.getFirstName(), newUser.getLastName(), newUser.getGender());
            pDAO.insert(newPerson);

            Generator generator = new Generator();
            generator.generate(newUser.getUserName(), newUser.getPersonID(), 4);

            AuthToken token = new AuthToken(newUser.getUserName());
            AuthTokenDAO aDAO = new AuthTokenDAO(conn);
            aDAO.insert(token);
            db.closeConnection(true);
            return new LoginResult(token.getToken(), newUser.getUserName(), newUser.getPersonID(), newPerson);
        } catch (DAOException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
                return new LoginResult(e.getMessage());
            } catch (DAOException d) {
                d.printStackTrace();
                return new LoginResult(d.getMessage());
            }
        }
    }
}
