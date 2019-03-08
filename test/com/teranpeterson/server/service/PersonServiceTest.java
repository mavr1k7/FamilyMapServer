package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.request.PersonRequest;
import com.teranpeterson.server.result.PersonResult;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class PersonServiceTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.clear();
        db.createTables();
        Connection conn = db.openConnection();
        AuthToken token = new AuthToken("username", "token");
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        authTokenDAO.insert(token);
        Person person = new Person("12345", "username", "first", "last", "m", "username");
        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(person);
        db.closeConnection(true);
    }

    @Test
    public void personPass() {
        PersonRequest request = new PersonRequest("12345", "token");
        PersonResult result = new PersonService().person(request);

        // Check that the event was successfully found
        assertEquals("12345", result.getPerson().getPersonID());
        assertTrue(result.isSuccess());
    }

    @Test
    public void personFail() {
        PersonRequest request = new PersonRequest("12345", "nottoken");
        PersonResult result = new PersonService().person(request);

        // Check that you can't load an event without an auth token
        assertNull(result.getPerson());
        assertFalse(result.isSuccess());
    }
}