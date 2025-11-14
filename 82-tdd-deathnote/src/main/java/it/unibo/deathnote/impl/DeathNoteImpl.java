package it.unibo.deathnote.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of the DeathNote interface.
 */
public class DeathNoteImpl implements DeathNote {

    /**
     * The Linked HashMap i.e. my death note with <Person key DeathCauseDetail value>.
     */
    private Map<Person, DeathCauseDetail> peopleToDie; // capire perche ? extends do not work

    /**
     * Constructor that receive already a created book.
     * 
     * @param peopleToDie book map of Person DeathCauseDetail
     */
    public DeathNoteImpl(final Map<Person, DeathCauseDetail> peopleToDie) {
        this.peopleToDie = peopleToDie;
    }

    /**
     * Default constructor.
     */
    public DeathNoteImpl() {
        this.peopleToDie = new LinkedHashMap<>();
    }

    /**
     * Getter of the book aka map of "persons".
     * 
     * @return final map book of Person and DeathCauseDetail.
     */
    public final Map<Person, DeathCauseDetail> getPeopleToDie() {
        return peopleToDie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1)
            throw new IllegalArgumentException("PUT AN ARGUMENT GREATER THAN 0(1) U DUMB FUCK");
        return RULES.get(ruleNumber - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(String name) {
        if (name == null)
            throw new NullPointerException("THE NAME SHOULD BE PASSED U PEACE OF SHIT!");
        final Person target = new Person(name, System.nanoTime());
        peopleToDie.put(target, new DeathCauseDetail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(String cause) {
        if (peopleToDie.isEmpty())
            throw new IllegalStateException("There are no person written in the Death note yet!");
        if (cause == null)
            throw new IllegalStateException("The cause should be NOT null!");

        boolean result = false;
        final var lastPersonWritten = getLastPerson();

        final long elapsed = System.nanoTime() - lastPersonWritten.getTimestamp();

        if (TimeUnit.NANOSECONDS.toMillis(elapsed) <= 40) {
            final var lastPersonsDeathCauseDetails = peopleToDie.get(lastPersonWritten);
            lastPersonsDeathCauseDetails.setCause(cause);
            lastPersonsDeathCauseDetails.setCauseTimestamp(System.nanoTime());
            result = true;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(String details) {
        if (peopleToDie.isEmpty())
            throw new IllegalStateException("There are no people in the Death note!");

        if (details == null)
            throw new IllegalStateException("The details of death are NULL!");

        final var lastPersonWritten = getLastPerson();
        final var deathDetails = peopleToDie.get(lastPersonWritten);
        boolean result = false;

        final long elapsed = System.nanoTime() - deathDetails.getCauseTimestamp();

        if (TimeUnit.NANOSECONDS.toMillis(elapsed) <= 6040) {
            deathDetails.setDetail(details);
            result = true;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(String name) {
        if (name == null)
            throw new IllegalArgumentException("Put the god damn vallid name, NOT NULL!");
        final var theGuy = getLastNamedPerson(name);
        if (theGuy == null)
            throw new IllegalArgumentException("The provided name is not written in the Death note!");
        return peopleToDie.get(theGuy).getCause().isEmpty() ? "heart attack" : peopleToDie.get(theGuy).getCause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(String name) {
        if (name == null)
            throw new IllegalArgumentException("Put the god damn vallid name, NOT NULL!");
        final var theGuy = getLastNamedPerson(name);
        if (theGuy == null)
            throw new IllegalArgumentException("The provided name is not written in the Death note!");
        return peopleToDie.get(theGuy).getDetail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(String name) {
        final var person = getLastNamedPerson(name);
        return person != null;
    }

    /**
     * Get the last person written in the death note
     * 
     * @return final Person object
     */
    public final Person getLastPerson() {
        Person nameRes = null;
        for (Map.Entry<Person, DeathCauseDetail> e : peopleToDie.entrySet()) {
            nameRes = e.getKey();
        }
        return nameRes;
    }

    /**
     * Get the last Person with the name passed by argument
     * 
     * @param name String of the person in the book
     * @return final Person with the name
     */
    public final Person getLastNamedPerson(final String name) {
        Person person = null;
        for (Map.Entry<Person, DeathCauseDetail> e : peopleToDie.entrySet()) {
            if (e.getKey().getName().equals(name))
                person = e.getKey();
        }
        return person;
    }

}
