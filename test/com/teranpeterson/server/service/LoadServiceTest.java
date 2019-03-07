package com.teranpeterson.server.service;

import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.LoadRequest;
import com.teranpeterson.server.result.LoadResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LoadServiceTest {
    private LoadRequest request;

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        User user1 = new User("username1", "password1", "email1", "firstname1", "lastname1", "m", "1");
        User user2 = new User("username2", "password2", "email2", "firstname2", "lastname2", "f", "2");
        users.add(user1);
        users.add(user2);

        Person person1 = new Person("1", null, "firstname1", "lastname1", "m", null, null, null);
        Person person2 = new Person("2", null, "firstname2", "lastname2", "f", null, null, null);
        persons.add(person1);
        persons.add(person2);

        Event event1 = new Event("8", null, "1", 6, 6, "country1", "city1", "type1", 2018);
        Event event2 = new Event("9", null, "2", 7, 7, "country2", "city2", "type2", 2019);
        events.add(event1);
        events.add(event2);

        request = new LoadRequest(users, persons, events);
        }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() {
        LoadService service = new LoadService();
        LoadResult result = service.load(request);
        if (result.isSuccess()) {
            System.out.println(result.getMessage());
        }
        else {
            System.out.println(result.getMessage());
        }
    }
}