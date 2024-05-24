package gymlife.model;

import gymlife.model.statistics.LimitedCounterImpl;
import gymlife.model.statistics.StatsConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * .
 */
class TestLimitedCounterImpl {
    /**
     * .
     */
    private final LimitedCounterImpl counter2 = new LimitedCounterImpl(StatsConstants.STARTING_STATS_LEVEL,
            StatsConstants.MAX_STATS_LEVEL);
    @Test
    void testIsMax() {
        counter2.multiIncrement(StatsConstants.MAX_STATS_LEVEL);
        assertTrue(counter2.isMax());
    }
    @Test
    void testIsMaxFalse() {
        counter2.multiIncrement(-StatsConstants.MAX_STATS_LEVEL);
        assertFalse(counter2.isMax());
    }
    @Test
    void testNotBiggerThanMax() {
        counter2.multiIncrement(StatsConstants.MAX_STATS_LEVEL);
        counter2.multiIncrement(StatsConstants.MAX_STATS_LEVEL);
        assertTrue(counter2.isMax());
        assertEquals(StatsConstants.MAX_STATS_LEVEL, counter2.getCount());
    }
}
