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

public class Generator {
    private Data data = new Data();
    private Database db = new Database();
    private String username;

    public Generator(String username) {
        loadFiles();
        this.username = username;
    }

    public void generate(String personID, int n) throws DAOException {
        Connection conn = db.openConnection();
        PersonDAO pDAO = new PersonDAO(conn);
        EventDAO eDAO = new EventDAO(conn);
        Person person = pDAO.find(personID);
        pDAO.deleteRelatives(username);
        eDAO.deleteEvents(username);
        db.closeConnection(true);

        generateEvents(personID, 1995);

        String fid = UUID.randomUUID().toString().substring(0, 6);
        String mid = UUID.randomUUID().toString().substring(0, 6);
        person.setFather(generatePerson("m", fid, mid, 1995 - 23, n - 1));
        person.setMother(generatePerson("f", mid, fid, 1995 - 23, n - 1));


        conn = db.openConnection();
        pDAO = new PersonDAO(conn);
        pDAO.update(personID, fid, mid);
        db.closeConnection(true);
    }

    private String generatePerson(String gender, String pid, String sid, int year, int n) throws DAOException {
        Person person = new Person(pid, username, getFirstName(gender), getSurname(), gender, sid);
        generateEvents(person.getPersonID(), year);

        if (n != 0) {
            Random rand = new Random();
            int x = rand.nextInt(4);
            String fid = UUID.randomUUID().toString().substring(0, 6);
            String mid = UUID.randomUUID().toString().substring(0, 6);
            person.setFather(generatePerson("m", fid, mid, year - 23 + x, n - 1));
            person.setMother(generatePerson("f", mid, fid, year - 23 + x, n - 1));
        }

        Connection conn = db.openConnection();
        PersonDAO pDAO = new PersonDAO(conn);
        pDAO.insert(person);
        db.closeConnection(true);
        return person.getPersonID();
    }

    private void generateEvents(String pid, int year) throws DAOException {
        Random rand = new Random();

        Location birthLoc = getLocation();
        Event birth = new Event(username, pid, birthLoc.getLatitude(), birthLoc.getLongitude(), birthLoc.getCountry(), birthLoc.getCity(), "Birth", year + rand.nextInt(3));
        Location marLoc = getLocation(year);
        Event marriage = new Event(username, pid, marLoc.getLatitude(), marLoc.getLongitude(), marLoc.getCountry(), marLoc.getCity(), "Marriage", year + 23);
        Location deathLoc = getLocation();
        Event death = new Event(username, pid, deathLoc.getLatitude(), deathLoc.getLongitude(), deathLoc.getCountry(), deathLoc.getCity(), "Death", year + 67 + rand.nextInt(20));

        Connection conn = db.openConnection();
        EventDAO eDAO = new EventDAO(conn);
        eDAO.insert(birth);
        if (marriage.getYear() < 2019) eDAO.insert(marriage);
        if (death.getYear() < 2019) eDAO.insert(death);
        db.closeConnection(true);
    }

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


    private String getSurname() {
        Random rand = new Random();
        return data.sur.get(rand.nextInt(152));
    }

    public Location getLocation() {
        Random rand = new Random();
        return data.locations.get(rand.nextInt(978));
    }

    private Location getLocation(int n) {
        return data.locations.get(n % 978);
    }

    public String getGender() {
        Random rand = new Random();
        return (rand.nextInt(2) == 1) ? "m" : "f";
    }

    private void loadFiles() {
        StringBuilder male = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/mnames.json"))) {
            while (scanner.hasNext()) {
                male.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Names mnames = new Gson().fromJson(male.toString(), Names.class);
        data.male = mnames.data;

        StringBuilder female = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/fnames.json"))) {
            while (scanner.hasNext()) {
                female.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Names fnames = new Gson().fromJson(female.toString(), Names.class);
        data.female = fnames.data;

        StringBuilder sur = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/snames.json"))) {
            while (scanner.hasNext()) {
                sur.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Names snames = new Gson().fromJson(sur.toString(), Names.class);
        data.sur = snames.data;

        StringBuilder loc = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("resources/json/locations.json"))) {
            while (scanner.hasNext()) {
                loc.append(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Locations locations = new Gson().fromJson(loc.toString(), Locations.class);
        data.locations = locations.data;
    }
}

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

class Locations {
    List<Location> data;

    public Locations() {
        data = new ArrayList<>();
    }
}