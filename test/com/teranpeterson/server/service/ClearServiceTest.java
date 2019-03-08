package com.teranpeterson.server.service;

import com.teranpeterson.server.result.ClearResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClearServiceTest {
    @Test
    public void clearPass() {
        ClearResult result = new ClearService().clear();
        assertTrue(result.isSuccess());
    }

    @Test
    public void clearFail() {
        // Can't think if a failing case
        ClearResult result = new ClearService().clear();
        assertTrue(result.isSuccess());
    }
}