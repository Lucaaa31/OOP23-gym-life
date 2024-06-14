package gymlife.model;

import gymlife.model.statistics.StatsConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gymlife.model.statistics.GameCounterImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGameCounter {
    private GameCounterImpl counter;
    @BeforeEach
    void setUp() {
        counter = new GameCounterImpl();
    }
    @Test
    void testGetCount() {
        assertEquals(0, counter.getCount());
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
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, counter.getCount());
    }
}
