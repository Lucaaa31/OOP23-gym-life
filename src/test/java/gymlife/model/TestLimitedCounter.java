package gymlife.model;

import gymlife.model.statistics.Counter;
import gymlife.model.statistics.LimitedCounter;
import gymlife.model.statistics.StatsConstants;
import gymlife.view.MainView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLimitedCounter {
    private LimitedCounter counter;
    private LimitedCounter counter2;
    @BeforeEach
    void setUp() {
        counter = new LimitedCounter(StatsConstants.MAX_STATS_LEVEL);
        counter2 = new LimitedCounter(StatsConstants.STARTING_STATS_LEVEL, StatsConstants.MAX_STATS_LEVEL);
    }
    @Test
    void testGetCount() {
        assertEquals(0, counter.getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, counter2.getCount());
    }
    @Test
    void testIncrement() {
        counter.increment();
        assertEquals(1, counter.getCount());
    }
    @Test
    void testDecrement() {
        counter.decrement();
        assertEquals(0, counter.getCount());
        counter.increment();
        counter.decrement();
        assertEquals(0, counter.getCount());
    }
    @Test
    void testMultiIncrement() {
        counter.multiIncrement(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5, counter.getCount());

        counter.multiIncrement(TestConstants.TEST_MULTI_INCREMENT_NEGATIVE_3);
        assertEquals(2, counter.getCount());
    }
    @Test
    void testResetCount() {
        counter.multiIncrement(10);
        counter.resetCount();
        assertEquals(0, counter.getCount());
    }
    @Test
    void testIsMax() {
        counter2.multiIncrement(StatsConstants.MAX_STATS_LEVEL);
        assertTrue(counter2.isMax());
    }
    @Test
    void testIsMaxFalse() {
        counter2.multiIncrement(- StatsConstants.MAX_STATS_LEVEL );
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
