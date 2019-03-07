package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.EventDAO;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;

import java.sql.Connection;

/**
 * Base class that all service classes must extend. Used to provide similar methods to all service objects.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class Service {
//    protected void generate(Connection conn, User user, int n) throws DAOException {
//        PersonDAO pDAO = new PersonDAO(conn);
//        EventDAO eDAO = new EventDAO(conn);
//
//        Clear events and persons related to user
//        pDAO.deleteRelatives(user.getUserName());
//        eDAO.deleteEvents(user.getUserName());
//        // TODO: All the hard stuff...
//    }

    protected String login(Connection conn, String userName) throws DAOException {
        AuthToken token = new AuthToken(userName);
        AuthTokenDAO aDAO = new AuthTokenDAO(conn);
        aDAO.insert(token);
        return token.getToken();
    }
}
