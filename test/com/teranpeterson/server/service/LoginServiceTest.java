package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.UserDAO;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.result.LoginResult;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;


public class LoginServiceTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.clear();
        db.createTables();
        Connection conn = db.openConnection();
        UserDAO userDAO = new UserDAO(conn);
        User user = new User("username", "password", "email", "first", "last", "m");
        userDAO.insert(user);
        db.closeConnection(true);
    }

    @Test
    public void loginPass() throws Exception {
        LoginRequest request = new LoginRequest("username", "password");
        LoginResult result = new LoginService().login(request);
        assertTrue(result.isSuccess());

        // Check that the auth token is correct
        Database db = new Database();
        Connection conn = db.openConnection();

        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        String username = authTokenDAO.validate(result.getAuthToken());
        assertEquals("username", username);

        db.closeConnection(true);
    }

    @Test
    public void loginFail() throws Exception {
        LoginRequest request = new LoginRequest("username", "notpassword");
        LoginResult result = new LoginService().login(request);
        assertFalse(result.isSuccess());

        // Check that no auth token was created
        Database db = new Database();
        Connection conn = db.openConnection();

        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        String username = authTokenDAO.validate(result.getAuthToken());
        assertNull(username);

        db.closeConnection(true);
    }
}