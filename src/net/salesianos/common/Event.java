package net.salesianos.common;

import java.io.Serializable;

/**
 * Represents an event in the system.
 * Stores event details such as name and ID.
 */
public class Event implements Serializable {
    private String name;
    private int id;

    /**
     * Constructor for Event.
     * 
     * @param id   The unique ID of the event.
     * @param name The name or description of the event.
     */
    public Event(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the event's name.
     * 
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the event's unique ID.
     * 
     * @return The ID of the event.
     */
    public int getId() {
        return id;
    }

    /**
     * Defines a default time before event notification.
     * 
     * @return The time before the event notification is triggered (10 seconds).
     */
    public long getTimeBeforeStart() {
        return 10000;
    }
}