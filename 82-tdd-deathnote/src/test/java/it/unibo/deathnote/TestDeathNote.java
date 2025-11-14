package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final String NAME1 = "Jason";
    private static final String NAME2 = "Marcus";
    private static final String NAME3 = "L";
    private static final String NAME4 = "N";

    @Test
    void testRules() {
        // 1.1
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new DeathNoteImpl().getRule(0);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new DeathNoteImpl().getRule(-1);
                });
        assertDoesNotThrow(() -> {
            new DeathNoteImpl().getRule(1);
        });

        // 1.2
        assertThrowsExactly(IllegalArgumentException.class,
                () -> {
                    new DeathNoteImpl().getRule(-1);
                });
        try {
            new DeathNoteImpl().getRule(0);
        } catch (final IllegalArgumentException e) {
            // 1.3
            assertNotNull(e.getMessage());
            // 1.4
            assertNotEquals(true, e.getMessage().isEmpty());
            // 1.5
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    void testEmptinessAndNullRules() {
        for (final var rule : DeathNote.RULES) {
            // 2.2
            assertNotNull(rule);
            // 2.3
            assertNotEquals("", rule);
        }
    }

    @Test
    void testNameInTheBook() {
        final DeathNote deathNote = new DeathNoteImpl();

        final String person1 = NAME1;
        // 3.1
        assertEquals(false, deathNote.isNameWritten(person1));

        // 3.2
        deathNote.writeName(person1);
        // 3.3
        assertEquals(true, deathNote.isNameWritten(person1));

        // 3.4
        // ho dovuto dichiarare la var della deathnote come tipo di implementazione...
        // senno non ho idea cosa si chiede
        // "verify that another human has not been written in the notebook"
        // guardo la size della mia mappa...
        assertEquals(false, deathNote.isNameWritten("L"));

        // 3.5
        assertEquals(false, deathNote.isNameWritten(""));

    }

    @Test
    void testCauseOfDeathWriting() throws InterruptedException {
        final DeathNote deathNote = new DeathNoteImpl();

        // 4.1
        assertThrows(IllegalStateException.class, () -> {
            deathNote.writeDeathCause("Stroking");
        });

        // 4.2
        deathNote.writeName(NAME1);

        // 4.3
        assertEquals("heart attack", deathNote.getDeathCause(NAME1));

        // 4.4
        deathNote.writeName(NAME2);

        // 4.5
        deathNote.writeDeathCause("Karting accident");

        // 4.6
        assertEquals("Karting accident", deathNote.getDeathCause(NAME2));

        // 4.7
        Thread.sleep(100);

        // 4.8
        deathNote.writeDeathCause("Heart attack");

        // 4.9
        assertEquals("Karting accident", deathNote.getDeathCause(NAME2));

    }

    @Test
    void testWriteDetails() throws InterruptedException {

        final DeathNote deathNote = new DeathNoteImpl();

        // 5.1
        assertThrows(RuntimeException.class, () -> {
            new DeathNoteImpl().writeDeathCause("who knows...?");
        });

        // 5.2
        deathNote.writeName(NAME3);

        // 5.3
        assertEquals(true, deathNote.getDeathDetails(NAME3).isEmpty());

        // 5.4
        final boolean detailsWrittenCorrectly = deathNote.writeDetails("ran for too long");

        // 5.5
        assertEquals(true, detailsWrittenCorrectly);
        assertEquals("ran for too long", deathNote.getDeathDetails(NAME3));

        // 5.6
        deathNote.writeName(NAME4);

        final int totalSleepTimeMs = 6100;
        // 5.7
        Thread.sleep(totalSleepTimeMs);

        // 5.8
        final boolean changeDetails = deathNote.writeDetails("N dropped down from a building beacause of heart attack");

        assertEquals(false, changeDetails);
        assertEquals("", deathNote.getDeathDetails(NAME4));

    }

}
