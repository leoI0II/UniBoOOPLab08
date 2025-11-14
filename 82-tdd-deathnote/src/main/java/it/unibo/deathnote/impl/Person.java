package it.unibo.deathnote.impl;

import java.util.Objects;

/**
 * Helper class to rappresent a person for the map key.
 */
public class Person {    
    /**
     * The name of the person.
     */
    private final String name;
    /**
     * The timestamp in nanoseconds when the person was written in the death note.
     */
    private final long timestamp; // in nano sec

    /**
     * Person default constructor.
     */
    public Person() {
        name = "";
        timestamp = 0;
    }

    /**
     * Person constructor.
     * 
     * @param name of the person
     * @param timestamp in nanoseconds.
     */
    public Person(final String name, final long timestamp) {
        this.name = Objects.requireNonNull(name);
        this.timestamp = timestamp;
    }

    /**
     * Return the name of the person.
     * 
     * @return String of the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the timestamp in nano seconds when the person was written in the book.
     * 
     * @return Long int timestamp in nanoseconds.
     */
    public long getTimestamp() {
        return timestamp;
    }

}
