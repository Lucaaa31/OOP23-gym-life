package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gymlife.model.api.StatsModel;

/**
 * This class contains unit tests for the StatsModel implementation.
 */
class TestStats {
    /**
     * Test case to verify the initiation of StatsModel.
     */
    @Test
    void testInitiation() {
        final StatsModel stats = new StatsModelImpl();
        final int initialMass = stats.getMass();
        assertEquals(initialMass, 0);
    }
    /**
     * Test case to verify the increment of humor in StatsModel.
     */
    @Test
    void testIncrement() {
        final StatsModel stats = new StatsModelImpl();
        stats.incHumor();
        assertEquals(stats.getHumor(), 1);
    }
}
