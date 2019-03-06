package com.teranpeterson.server.service;

import com.teranpeterson.server.result.ClearResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClearServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void clear() {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
        if (result.isSuccess()) {
            System.out.println(result.getMessage());
        }
        else {
            System.out.println(result.getMessage());
        }
    }
}