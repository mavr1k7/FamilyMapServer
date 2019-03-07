package com.teranpeterson.server.service;

import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.result.LoginResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceTest {
    private LoginRequest request;

    @Before
    public void setUp() throws Exception {
        request = new LoginRequest("John", "password1");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws Exception {
        LoginService service = new LoginService();
        LoginResult result = service.login(request);
        if (result.isSuccess()) {
            System.out.println(result.getAuthToken());
        }
        else {
            System.out.println(result.getMessage());
        }
    }
}