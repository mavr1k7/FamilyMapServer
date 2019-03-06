package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;

import java.sql.Connection;

/**
 * Base class that all service classes must extend. Used to provide similar methods to all service objects.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class Service {
    protected void generate(Connection conn, User user, int n) throws DAOException {
        // Generation 0
        Person newPerson = new Person(user.getPersonID(), null, user.getFirstname(), user.getLastname(), user.getGender(), null, null, null);
        PersonDAO pDAO = new PersonDAO(conn);
        pDAO.insert(newPerson);
        // TODO
    }

    protected String login(Connection conn, String username) throws DAOException {
        AuthToken token = new AuthToken(username);
        AuthTokenDAO aDAO = new AuthTokenDAO(conn);
        aDAO.insert(token);
        return token.getToken();
    }
}
