package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.*;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.result.ClearResult;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ClearServiceTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.clear();
        db.createTables();
        Connection conn = db.openConnection();

        AuthToken token = new AuthToken("username", "token");
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        authTokenDAO.insert(token);

        Event event = new Event("12345", "username", "12345", 1, 1, "country", "city", "type", 2019);
        EventDAO eventDAO = new EventDAO(conn);
        eventDAO.insert(event);

        Person person = new Person("12345", "first", "last", "m");
        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(person);

        User user = new User("username", "password", "email", "first", "last", "m");
        UserDAO userDAO = new UserDAO(conn);
        userDAO.insert(user);

        db.closeConnection(true);
    }

    @Test
    public void clearPass() throws Exception {
        ClearResult result = new ClearService().clear();
        assertTrue(result.isSuccess());
        Database db = new Database();
        db.createTables(); // Clear removes all tables. Need to recreate tables to check that they are empty
        Connection conn = db.openConnection();

        // Check that auth token was removed
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        String username = authTokenDAO.validate("token");
        assertNull(username);

        // Check that event was removed
        EventDAO eventDAO = new EventDAO(conn);
        Event event = eventDAO.find("12345");
        assertNull(event);

        // Check that person was removed
        PersonDAO personDAO = new PersonDAO(conn);
        Person person = personDAO.find("12345");
        assertNull(person);

        // Check that user was removed
        UserDAO userDAO = new UserDAO(conn);
        User user = userDAO.find("username");
        assertNull(user);

        db.closeConnection(true);
    }

    @Test
    public void clearFail() {
        // Check that we can run two clears in a row without problem
        ClearResult result1 = new ClearService().clear();
        ClearResult result2 = new ClearService().clear();
        assertTrue(result1.isSuccess());
        assertTrue(result2.isSuccess());
    }
}