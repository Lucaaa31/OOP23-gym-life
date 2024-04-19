package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import gymlife.model.api.StatsModel;
import gymlife.utility.StatsType;

/**
 * This class contains unit tests for the StatsModel implementation.
 */
class TestStats {

    private StatsModel stats;

    @BeforeEach
    void setUp() {
        this.stats = new StatsModelImpl();
        stats.resetAll();
        assertEquals(0, stats.getStats(StatsType.HUMOR));
        assertEquals(0, stats.getStats(StatsType.STAMINA));
        assertEquals(0, stats.getStats(StatsType.LEG_MASS));
        assertEquals(0, stats.getStats(StatsType.BACK_MASS));
        assertEquals(0, stats.getStats(StatsType.CHEST_MASS));
    }
    /**
     * Test case to verify the increment of humor in StatsModel.
     */
    @Test
    void testIncrementOne() {
        stats.increase(StatsType.HUMOR);
        assertEquals(1, stats.getStats(StatsType.HUMOR));
        stats.decrease(StatsType.HUMOR);
        assertEquals(0, stats.getStats(StatsType.HUMOR));

        stats.increase(StatsType.STAMINA);
        assertEquals(1, stats.getStats(StatsType.STAMINA));
        stats.decrease(StatsType.STAMINA);
        assertEquals(0, stats.getStats(StatsType.STAMINA));

        stats.increase(StatsType.LEG_MASS);
        assertEquals(1, stats.getStats(StatsType.LEG_MASS));
        stats.decrease(StatsType.LEG_MASS);
        assertEquals(0, stats.getStats(StatsType.LEG_MASS));

        stats.increase(StatsType.BACK_MASS);
        assertEquals(1, stats.getStats(StatsType.BACK_MASS));
        stats.decrease(StatsType.BACK_MASS);
        assertEquals(0, stats.getStats(StatsType.BACK_MASS));

        stats.increase(StatsType.CHEST_MASS);
        assertEquals(1, stats.getStats(StatsType.CHEST_MASS));
        stats.decrease(StatsType.CHEST_MASS);
        assertEquals(0, stats.getStats(StatsType.CHEST_MASS));
    }

    @Test
    void testMultincrement() {
        stats.multiIncrement(StatsType.HUMOR,5);
        assertEquals(5, stats.getStats(StatsType.HUMOR));
        
        stats.multiIncrement(StatsType.HUMOR, -5);
        assertEquals(0, stats.getStats(StatsType.HUMOR));
    }
}
