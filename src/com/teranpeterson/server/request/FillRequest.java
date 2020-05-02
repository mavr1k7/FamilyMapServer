package com.teranpeterson.server.request;

/**
 * Contains information about the Fill Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class FillRequest {
    /**
     * User's userName
     */
    private String userName;
    /**
     * Number of generations to create
     */
    private int generations;

    /**
     * Creates a fill request with the given information and default 4 generations
     *
     * @param userName User's userName
     */
    public FillRequest(String userName) {
        this(userName, 4);
    }

    /**
     * Creates a fill request with the given information
     *
     * @param userName    User's userName
     * @param generations Number of generations to create
     */
    public FillRequest(String userName, int generations) {
        this.userName = userName;
        this.generations = generations;
    }

    /**
     * Gets the user's userName
     *
     * @return User's userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's userName
     *
     * @param userName User's userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the number of generations to create
     *
     * @return Number of generations to create
     */
    public int getGenerations() {
        return generations;
    }

    /**
     * Sets the number of generations to create
     *
     * @param generations Number of generations to create
     */
    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
