package gymlife.model;

import gymlife.model.statistics.StatsModelImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import gymlife.model.statistics.api.StatsModel;
import gymlife.model.statistics.StatsConstants;
import gymlife.model.statistics.StatsType;

/**
 * This class contains unit tests for the StatsModel implementation.
 */
class TestStats {

    @Test
    void testInitialization() {
        final StatsModel stats = new StatsModelImpl();
        stats.resetAll();
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.HAPPINESS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.STAMINA));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.LEG_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.BACK_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(StatsConstants.STARTING_MASS_LEVEL, stats.getStats(StatsType.MASS));
        stats.increase(StatsType.MASS);
        assertEquals(StatsConstants.STARTING_MASS_LEVEL * 2, stats.getStats(StatsType.MASS));
        stats.decrease(StatsType.MASS);
        assertEquals(3, stats.getStats(StatsType.MASS));
        stats.resetAll();
    }
    @Test
    void testIncrementDecrement() {
        final StatsModel stats = new StatsModelImpl();
        assertEquals(3, stats.getStats(StatsType.MASS));
        assertEquals(1, stats.getStats(StatsType.LEG_MASS));
        assertEquals(1, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(1, stats.getStats(StatsType.BACK_MASS));

        stats.increase(StatsType.MASS);
        assertEquals(StatsConstants.STARTING_MASS_LEVEL * 2, stats.getStats(StatsType.MASS));
        assertEquals(2, stats.getStats(StatsType.LEG_MASS));
        assertEquals(2, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(2, stats.getStats(StatsType.BACK_MASS));

        stats.decrease(StatsType.MASS);
        assertEquals(3, stats.getStats(StatsType.MASS));
        assertEquals(1, stats.getStats(StatsType.LEG_MASS));
        assertEquals(1, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(1, stats.getStats(StatsType.BACK_MASS));
        stats.decrease(StatsType.MASS);
        assertEquals(0, stats.getStats(StatsType.MASS));
        assertEquals(0, stats.getStats(StatsType.LEG_MASS));
        assertEquals(0, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(0, stats.getStats(StatsType.BACK_MASS));

        stats.decrease(StatsType.MASS);
        assertEquals(0, stats.getStats(StatsType.MASS));
        assertEquals(0, stats.getStats(StatsType.LEG_MASS));
        assertEquals(0, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(0, stats.getStats(StatsType.BACK_MASS));
        stats.increase(StatsType.LEG_MASS);
        assertEquals(1, stats.getStats(StatsType.MASS));
        assertEquals(1, stats.getStats(StatsType.LEG_MASS));
        assertEquals(0, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(0, stats.getStats(StatsType.BACK_MASS));
        stats.increase(StatsType.CHEST_MASS);
        assertEquals(2, stats.getStats(StatsType.MASS));
        assertEquals(1, stats.getStats(StatsType.LEG_MASS));
        assertEquals(1, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(0, stats.getStats(StatsType.BACK_MASS));
        stats.increase(StatsType.LEG_MASS);
        assertEquals(3, stats.getStats(StatsType.MASS));
        assertEquals(2, stats.getStats(StatsType.LEG_MASS));
        stats.increase(StatsType.STAMINA);
        assertEquals(StatsConstants.MAX_STATS_LEVEL, stats.getStats(StatsType.STAMINA));
        stats.decrease(StatsType.STAMINA);
        assertEquals(StatsConstants.MAX_STATS_LEVEL - 1, stats.getStats(StatsType.STAMINA));
        stats.resetAll();
    }
    @Test
    void testMultiIncrement() {
        final StatsModel stats = new StatsModelImpl();
        stats.multiIncrementStats(StatsType.BACK_MASS, TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(StatsConstants.STARTING_STATS_LEVEL + TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5,
                stats.getStats(StatsType.BACK_MASS));
        stats.multiIncrementStats(StatsType.BACK_MASS, - TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.BACK_MASS));
        stats.resetAll();
    }
}
