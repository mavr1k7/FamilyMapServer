package com.teranpeterson.server.service;

import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.request.PersonRequest;
import com.teranpeterson.server.result.PersonResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonServiceTest {
    private PersonRequest request;


    @Before
    public void setUp() throws Exception {
        request = new PersonRequest("token");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void person() {
        PersonService service = new PersonService();
        PersonResult result = service.person(request);
        if (result.isSuccess()) {
            for (Person e : result.getData()) {
                System.out.println(e.getPersonID());
            }
        }
        else {
            System.out.println(result.getMessage());
        }
    }
}