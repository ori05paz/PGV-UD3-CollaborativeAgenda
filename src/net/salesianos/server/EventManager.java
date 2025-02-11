package net.salesianos.server;

import java.util.concurrent.ConcurrentHashMap;

import net.salesianos.common.Event;

/**
 * Manages the storage and retrieval of events in a concurrent-safe manner.
 */
public class EventManager {
    private static final ConcurrentHashMap<Integer, Event> events = new ConcurrentHashMap<>();
    private static int eventIdCounter = 0;

    /**
     * Adds a new event to the event list.
     * 
     * @param event The event to add.
     * @return The unique ID assigned to the event.
     */
    public static synchronized int addEvent(Event event) {
        events.put(event.getId(), event);
        return event.getId();
    }

    /**
     * Removes an event from the event list.
     * 
     * @param id The ID of the event to remove.
     * @return True if the event was successfully removed, false otherwise.
     */
    public static synchronized boolean removeEvent(int id) {
        return events.remove(id) != null;
    }

    /**
     * Generates a unique event ID.
     * 
     * @return A unique event ID.
     */
    public static synchronized int generateEventId() {
        return ++eventIdCounter;
    }

    /**
     * Retrieves an event by its ID.
     * 
     * @param id The ID of the event.
     * @return The event if found, null otherwise.
     */
    public static synchronized Event getEvent(int id) {
        return events.get(id);
    }

}