package com.teranpeterson.server.service;

import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.request.EventRequest;
import com.teranpeterson.server.result.EventResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventServiceTest {
    private EventRequest request;

    @Before
    public void setUp() throws Exception {
        request = new EventRequest("token");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void event() {
        EventService service = new EventService();
        EventResult result = service.event(request);
        if (result.isSuccess()) {
            for (Event e : result.getData()) {
                System.out.println(e.getEventID());
            }
        }
        else {
            System.out.println(result.getMessage());
        }
    }
}