package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.EventDAO;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.request.EventRequest;
import com.teranpeterson.server.result.EventResult;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class EventServiceTest {
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
        db.closeConnection(true);
    }

    @Test
    public void eventPass() {
        EventRequest request = new EventRequest("12345", "token");
        EventResult result = new EventService().event(request);
        assertTrue(result.isSuccess());
    }

    @Test
    public void eventFail() {
        EventRequest request = new EventRequest("12345", "nottoken");
        EventResult result = new EventService().event(request);
        assertFalse(result.isSuccess());
    }
}