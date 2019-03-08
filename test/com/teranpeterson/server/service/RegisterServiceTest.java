package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.EventDAO;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.dao.UserDAO;
import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.LoginResult;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class RegisterServiceTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.clear();
        db.createTables();
        Connection conn = db.openConnection();
        UserDAO userDAO = new UserDAO(conn);
        User user = new User("username", "email", "password", "first", "last", "m");
        userDAO.insert(user);
        db.closeConnection(true);
    }

    @Test
    public void registerPass() throws Exception {
        RegisterRequest request = new RegisterRequest("sam", "music", "sam@gmail.com", "Sam", "Smith", "m");
        LoginResult result = new RegisterService().register(request);
        assertTrue(result.isSuccess());

        Database db = new Database();
        Connection conn = db.openConnection();

        // Check that user was added to database
        UserDAO userDAO = new UserDAO(conn);
        User user = userDAO.find("sam");
        assertEquals("music", user.getPassword());

        // Check that ancestors and events were added for user
        PersonDAO personDAO = new PersonDAO(conn);
        List<Person> personList = personDAO.findRelatives("sam");
        assertNotNull(personList);
        assertEquals(31, personList.size());

        EventDAO eventDAO = new EventDAO(conn);
        List<Event> eventList = eventDAO.personEvents("sam");
        assertNotNull(eventList);

        db.closeConnection(true);
    }

    @Test
    public void registerFail() throws Exception {
        RegisterRequest request = new RegisterRequest("username", "music", "sam@gmail.com", "Sam", "Smith", "m");
        LoginResult result = new RegisterService().register(request);
        assertFalse(result.isSuccess());
        assertEquals("ERROR: Username is already in use", result.getMessage());

        // Check that duplicate username wasn't able to be used
        Database db = new Database();
        Connection conn = db.openConnection();

        UserDAO userDAO = new UserDAO(conn);
        User user = userDAO.find("username");
        assertNotEquals("music", user.getPassword());

        db.closeConnection(true);
    }
}