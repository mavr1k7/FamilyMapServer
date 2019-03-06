package com.teranpeterson.server.service;

import com.teranpeterson.server.request.FillRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FillServiceTest {
    private FillRequest request;


    @Before
    public void setUp() throws Exception {
        request = new FillRequest("John");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fill() {
        // Need to finish generate() first
    }
}