package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import gymlife.model.api.StatsModel;
import gymlife.utility.StatsConstants;
import gymlife.utility.StatsType;

/**
 * This class contains unit tests for the StatsModel implementation.
 */
class TestStats {


    @Test
    void setUp() {
        StatsModel stats = new StatsModelImpl();
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.HUMOR));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.STAMINA));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.LEG_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.BACK_MASS));
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats(StatsType.CHEST_MASS));
    }


}
