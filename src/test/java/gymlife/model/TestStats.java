package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import gymlife.model.api.StatsModel;
import gymlife.utility.ScenariosType;
import gymlife.utility.StatsConstants;
import gymlife.utility.StatsType;

/**
 * This class contains unit tests for the StatsModel implementation.
 */
class TestStats {

    @Test
    void setUp() {
        StatsModel stats = new StatsModelImpl();
        stats.resetAll();
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.HUMOR));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.STAMINA));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.LEG_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.BACK_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL * 3, stats.getStats(StatsType.MASS));
        stats.increase(StatsType.MASS);
        assertEquals(6, stats.getStats(StatsType.MASS));
        stats.decrease(StatsType.MASS);
        assertEquals(3, stats.getStats(StatsType.MASS));
        stats.resetAll();
    }

     
    @Test
    void testIncrementDecrement() {
        StatsModel stats = new StatsModelImpl();
        assertEquals(3, stats.getStats(StatsType.MASS));
        assertEquals(1, stats.getStats(StatsType.LEG_MASS));
        assertEquals(1, stats.getStats(StatsType.CHEST_MASS));
        assertEquals(1, stats.getStats(StatsType.BACK_MASS));

        stats.increase(StatsType.MASS);
        assertEquals(6, stats.getStats(StatsType.MASS));
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
        assertEquals(2, stats.getStats(StatsType.STAMINA));
        stats.decrease(StatsType.STAMINA);
        assertEquals(1, stats.getStats(StatsType.STAMINA));
        stats.decrease(StatsType.STAMINA);
        assertEquals(0, stats.getStats(StatsType.STAMINA));
        stats.decrease(StatsType.STAMINA);
        assertEquals(0, stats.getStats(StatsType.STAMINA));

        stats.increase(StatsType.HUMOR);
        assertEquals(2, stats.getStats(StatsType.HUMOR));
        stats.resetAll();
    }
}
