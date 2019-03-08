package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.request.PersonRequest;
import com.teranpeterson.server.result.PersonResult;

import java.sql.Connection;
import java.util.List;

/**
 * Returns the single Person object with the specified ID or returns ALL family members of the current user.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class PersonService {
    /**
     * Create a blank person service object
     */
    public PersonService() {

    }

    /**
     * If the request object contains a personID, that person is returned. Otherwise ALL family members related
     * to the current user (determined by the auth token) are returned.
     *
     * @param request Information about the person(s) to return
     * @return Information about the person(s)
     */
    public PersonResult person(PersonRequest request) {
        Database db = new Database();
        try {
            db.createTables();
            Connection conn = db.openConnection();
            AuthTokenDAO aDAO = new AuthTokenDAO(conn);
            String userName = aDAO.validate(request.getAuthToken());

            if (userName == null) {
                try {
                    db.closeConnection(false);
                    return new PersonResult("ERROR: Invalid auth token");
                } catch (DAOException e) {
                    e.printStackTrace();
                    return new PersonResult(e.getMessage());
                }
            }

            PersonDAO pDAO = new PersonDAO(conn);
            if (request.getPersonID() == null) {
                List<Person> list = pDAO.findRelatives(userName);
                db.closeConnection(true);
                return new PersonResult(list);
            } else {
                Person person = pDAO.find(request.getPersonID());
                db.closeConnection(true);
                if (person == null) return new PersonResult("ERROR: Invalid personID");
                else if (!person.getDescendant().equals(userName)) return new PersonResult("ERROR: Person is not related to you");
                else return new PersonResult(person);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
                return new PersonResult(e.getMessage());
            } catch (DAOException d) {
                d.printStackTrace();
                return new PersonResult(d.getMessage());
            }
        }
    }
}
