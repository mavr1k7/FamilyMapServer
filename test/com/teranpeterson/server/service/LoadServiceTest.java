package com.teranpeterson.server.service;

import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.LoadRequest;
import com.teranpeterson.server.result.LoadResult;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoadServiceTest {
    private LoadRequest request;

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        users.add(new User("username1", "password1", "email1", "firstname1", "lastname1", "m", "1"));
        users.add(new User("username2", "password2", "email2", "firstname2", "lastname2", "f", "2"));

        persons.add(new Person("1", null, "firstname1", "lastname1", "m", null, null, null));
        persons.add(new Person("2", null, "firstname2", "lastname2", "f", null, null, null));

        events.add(new Event("8", null, "1", 6, 6, "country1", "city1", "type1", 2018));
        events.add(new Event("9", null, "2", 7, 7, "country2", "city2", "type2", 2019));

        request = new LoadRequest(users, persons, events);
    }

    @Test
    public void loadPass() {
        LoadService service = new LoadService();
        LoadResult result = service.load(request);
        assertTrue(result.isSuccess());
    }

    @Test
    public void loadFail() {
        List<User> users = new ArrayList<>();
        users.add(new User("username1", "password1", "email1", "firstname1", "lastname1", "m", "1"));
        users.add(new User("username1", "password2", "email2", "firstname2", "lastname2", "f", "2"));
        request.setUsers(users);

        LoadService service = new LoadService();
        LoadResult result = service.load(request);
        assertFalse(result.isSuccess());
    }
}