package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.dao.UserDAO;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.FillRequest;
import com.teranpeterson.server.result.FillResult;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class FillServiceTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.clear();
        db.createTables();
        Connection conn = db.openConnection();
        UserDAO userDAO = new UserDAO(conn);
        User user = new User("username", "password", "email", "first", "last", "m", "12345");
        userDAO.insert(user);
        PersonDAO personDAO = new PersonDAO(conn);
        Person person = new Person("12345", "username", "first", "last", "m");
        personDAO.insert(person);
        db.closeConnection(true);
    }

    @Test
    public void fillPass() {
        FillRequest request = new FillRequest("username");
        FillResult result = new FillService().fill(request);

        // Check that the correct number of people and events were added
        assertEquals("Successfully added 31 persons and 122 events to the database.", result.getMessage());
        assertTrue(result.isSuccess());
    }

    @Test
    public void fillFail() {
        FillRequest request = new FillRequest("not");
        FillResult result = new FillService().fill(request);

        // Check that no data was added for invalid user
        assertEquals("ERROR: Invalid userName", result.getMessage());
        assertFalse(result.isSuccess());
    }
}