package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.UserDAO;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.RegisterRequest;
import com.teranpeterson.server.result.LoginResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registerPass() throws Exception {
        RegisterRequest request = new RegisterRequest("sam", "music", "sam@gmail.com", "Sam", "Smith", "m");
        LoginResult result = new RegisterService().register(request);
        assertTrue(result.isSuccess());
    }

    @Test
    public void registerFail() throws Exception {
        RegisterRequest request = new RegisterRequest("username", "music", "sam@gmail.com", "Sam", "Smith", "m");
        LoginResult result = new RegisterService().register(request);
        assertFalse(result.isSuccess());
    }
}