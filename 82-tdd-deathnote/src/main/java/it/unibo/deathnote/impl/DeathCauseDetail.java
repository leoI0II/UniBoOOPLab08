package it.unibo.deathnote.impl;

import java.util.Objects;

/**
 * Helper class to rappresent the data of the death cause, 
 * when it was specified (in nanoseconds)
 * and the detail of death.
 */
public class DeathCauseDetail {

    /**
     * Default death of the person is a heart attack.
     */
    static final String DEFAULT_CAUSE_OF_DEATH = "heart attack";

    private String cause;
    private long causeTimestamp;
    private String detail;

    /**
     * Default constructor.
     * Just create empty objects of Strings for cause and detail, timestamp to zero.
     */
    DeathCauseDetail() {
        cause = new String();
        causeTimestamp = System.nanoTime();
        detail = new String();
    }

    /**
     * Call the def constructor. Then set cause to the passed cause argument.
     * 
     * @param cause String
     */
    DeathCauseDetail(final String cause) {
        this();
        this.cause = Objects.requireNonNull(cause);
    }

    /**
     * Call the def constructor.
     * Set cause and detail Strings.
     * 
     * @param cause String
     * @param detail String
     */
    DeathCauseDetail(final String cause, final String detail) {
        this();
        this.cause = Objects.requireNonNull(cause);
        this.detail = Objects.requireNonNull(detail);
    }

    /**
     * Get the cause.
     * 
     * @return the String cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * Setting of the cause of death.
     * 
     * @param cause in String
     */
    public void setCause(final String cause) {
        this.cause = cause;
    }

    /**
     * Getter timestamp.
     * 
     * @return long int timestamp in nanoseconds
     */
    public long getCauseTimestamp() {
        return causeTimestamp;
    }

    /**
     * Setting timestamp in nanoseconds.
     * 
     * @param causeTimestamp long int in nanoseconds.
     */
    public void setCauseTimestamp(final long causeTimestamp) {
        this.causeTimestamp = causeTimestamp;
    }

    /**
     * Getter for details of death.
     * 
     * @return String
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Setter for detailing death.
     * 
     * @param detail String
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

}
