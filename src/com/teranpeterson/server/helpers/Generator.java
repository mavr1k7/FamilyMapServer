package com.teranpeterson.server.helpers;

import com.google.gson.Gson;
import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.EventDAO;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.*;

/**
 * Generates random ancestors and corresponding events for a user. Uses json files with names and locations to randomly generate
 * location for the persons and events. Uses sudo random calculations to keep events in chronological order
 *
 * @author Teran Peterson
 * @version v0.1.2
 */
public class Generator {
    private Data data = new Data();
    private Database db = new Database();
    private String username;

    /**
     * Constructor for Generator. Loads the json files and sets the username
     */
    public Generator() {
        loadFiles();
    }

    /**
     * Loads the current person and recursively adds specified number of generations. Each generation is approximately 23
     * years apart and uses sudo random numbers to add variability. Birth, marriage and death are generated for each new person
     *
     * @param personID       PersonID of user's person
     * @param numGenerations Number of generations back to go
     * @throws DAOException Database exception
     */
    public void generate(String personID, int numGenerations) throws DAOException {
        // Loads the current user's person info
        Connection conn = db.openConnection();
        PersonDAO personDAO = new PersonDAO(conn);
        EventDAO eventDAO = new EventDAO(conn);
        Person person = personDAO.find(personID);
        this.username = person.getDescendant();

        // Removes all persons and events related to the user
        personDAO.deleteRelatives(username);
        eventDAO.deleteEvents(username);
        db.closeConnection(true);

        // Generates events for the user
        generateEvents(personID, 1997);

        // Calls recursive function to create ancestors
        String fid = UUID.randomUUID().toString().substring(0, 6);
        String mid = UUID.randomUUID().toString().substring(0, 6);
        person.setFather(generatePerson("m", fid, mid, 1997 - 23, numGenerations - 1));
        person.setMother(generatePerson("f", mid, fid, 1997 - 23, numGenerations - 1));

        // Updates the user's person data in the database
        conn = db.openConnection();
        personDAO = new PersonDAO(conn);
        personDAO.insert(person);
        db.closeConnection(true);
    }

    /**
     * Recursive function that creates ancestors for a person. A person is created and a set of events are generated for that
     * person. Recursively adds a mother and father to the created person. Adds everything to the database.
     *
     * @param gender         Gender (m or f)
     * @param personID       ID assigned by last iteration (used by spouse)
     * @param spouseID       Spouse's id
     * @param year           Current year to create events with
     * @param numGenerations Number of generations left to go
     * @return ID of the person just created
     * @throws DAOException Database exception
     */
    private String generatePerson(String gender, String personID, String spouseID, int year, int numGenerations) throws DAOException {
        // Creates a new person and corresponding events
        Person person = new Person(personID, username, getFirstName(gender), getSurname(), gender, spouseID);
        generateEvents(person.getPersonID(), year);

        // Recursively creates ancestors for that person
        if (numGenerations != 0) {
            Random rand = new Random();
            int r = rand.nextInt(4);
            String fatherID = UUID.randomUUID().toString().substring(0, 6);
            String motherID = UUID.randomUUID().toString().substring(0, 6);
            person.setFather(generatePerson("m", fatherID, motherID, year - 23 + r, numGenerations - 1));
            person.setMother(generatePerson("f", motherID, fatherID, year - 23 + r, numGenerations - 1));
        }

        // Adds new person to the database
        Connection conn = db.openConnection();
        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(person);
        db.closeConnection(true);
        return person.getPersonID();
    }

    /**
     * Creates a birth, marriage and death for a given person. Uses sudo random numbers to add variability to dates. Year is
     * used to set location for marriage so that it matches for husband and wife.
     *
     * @param personID ID of person the event belongs to
     * @param year     Current year to make events with
     * @throws DAOException Database exception
     */
    private void generateEvents(String personID, int year) throws DAOException {
        Random rand = new Random();

        // Creates birth with random location and date within 3 years of current year
        Location birthLoc = getLocation();
        Event birth = new Event(username, personID, birthLoc.getLatitude(), birthLoc.getLongitude(), birthLoc.getCountry(), birthLoc.getCity(), "Birth", year + rand.nextInt(3));

        // Creates baptism with random location and date 10 years after current year
        Location baptismLoc = getLocation();
        Event baptism = new Event(username, personID, baptismLoc.getLatitude(), baptismLoc.getLongitude(), baptismLoc.getCountry(), baptismLoc.getCity(), "Baptism", year + 10);

        // Creates marriage with location specified by year and date 23 years after birth
        Location marLoc = getLocation(year);
        Event marriage = new Event(username, personID, marLoc.getLatitude(), marLoc.getLongitude(), marLoc.getCountry(), marLoc.getCity(), "Marriage", year + 23);

        // Creates death with random location and date 67-87 years after current year
        Location deathLoc = getLocation();
        Event death = new Event(username, personID, deathLoc.getLatitude(), deathLoc.getLongitude(), deathLoc.getCountry(), deathLoc.getCity(), "Death", year + 67 + rand.nextInt(20));

        // Only adds event to database if it happened in the past
        Connection conn = db.openConnection();
        EventDAO eventDAO = new EventDAO(conn);
        if (birth.getYear() < 2019) eventDAO.insert(birth);
        if (baptism.getYear() < 2019) eventDAO.insert(baptism);
        if (marriage.getYear() < 2019) eventDAO.insert(marriage);
        if (death.getYear() < 2019) eventDAO.insert(death);
        db.closeConnection(true);
    }

    /**
     * Return a random first name for a specified gender using predefined list
     *
     * @param gender Gender of name (m or f)
     * @return Random first name
     */
    private String getFirstName(String gender) {
        Random rand = new Random();
        if (gender.equals("m")) {
            return data.male.get(rand.nextInt(144));
        }
        if (gender.equals("f")) {
            return data.female.get(rand.nextInt(147));
        }
        return null;
    }

    /**
     * Returns a random last name using predefined list
     *
     * @return Random surname
     */
    private String getSurname() {
        Random rand = new Random();
        return data.sur.get(rand.nextInt(152));
    }

    /**
     * Returns a random location using predefined list
     *
     * @return Random location
     */
    private Location getLocation() {
        Random rand = new Random();
        return data.locations.get(rand.nextInt(978));
    }

    /**
     * Returns a location based on number given. Used for marriage events
     *
     * @param n Number for location
     * @return Sudo random location
     */
    private Location getLocation(int n) {
        return data.locations.get(n % 978);
    }

    /**
     * Loads lists of random names and locations from 4 different json files and stores them in a Data object
     */
    private void loadFiles() {
        // Load male names
        StringBuilder male = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/mnames.json"))) {
            while (scanner.hasNext()) {
                male.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Deserialize and add to data object
        Names mnames = new Gson().fromJson(male.toString(), Names.class);
        data.male = mnames.data;

        // Load female names
        StringBuilder female = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/fnames.json"))) {
            while (scanner.hasNext()) {
                female.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Deserialize and add to data object
        Names fnames = new Gson().fromJson(female.toString(), Names.class);
        data.female = fnames.data;

        // Load surnames
        StringBuilder sur = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/snames.json"))) {
            while (scanner.hasNext()) {
                sur.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Deserialize and add to data object
        Names snames = new Gson().fromJson(sur.toString(), Names.class);
        data.sur = snames.data;

        // Load locations
        StringBuilder loc = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/locations.json"))) {
            while (scanner.hasNext()) {
                loc.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Deserialize and add to data object
        Locations locations = new Gson().fromJson(loc.toString(), Locations.class);
        data.locations = locations.data;
    }
}

/**
 * Class containing lists of names and locations to use in the generator
 */
class Data {
    List<String> male;
    List<String> female;
    List<String> sur;
    List<Location> locations;

    Data() {
        male = new ArrayList<>();
        female = new ArrayList<>();
        sur = new ArrayList<>();
        locations = new ArrayList<>();
    }
}

/**
 * Temporary class used to deserialize json files
 */
class Names {
    List<String> data;

    public Names() {
        data = new ArrayList<>();
    }

    public void out() {
        for (String s : data) {
            System.out.println(s);
        }
    }
}

/**
 * Temporary class used to deserialize json files
 */
class Locations {
    List<Location> data;

    public Locations() {
        data = new ArrayList<>();
    }
}