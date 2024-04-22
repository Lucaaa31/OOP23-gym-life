package gymlife.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gymlife.model.api.Counter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCounter {
    private Counter counter;

    @BeforeEach
    public void setUp() {
        counter = new Counter();
    }

    @Test
    public void testGetCount() {
        assertEquals(0, counter.getCount());
    }

    @Test
    public void testIncrement() {
        counter.increment();
        assertEquals(1, counter.getCount());
    }

    @Test
    public void testDecrement() {
        counter.decrement();
        assertEquals(0, counter.getCount());
        
        counter.increment();
        counter.decrement();
        assertEquals(0, counter.getCount());
    }

    @Test
    public void testMultiIncrement() {
        counter.multiIncrement(5);
        assertEquals(5, counter.getCount());

        counter.multiIncrement(-3);
        assertEquals(2, counter.getCount());
    }

    @Test
    public void testResetCount() {
        counter.multiIncrement(10);
        counter.resetCount();
        assertEquals(0, counter.getCount());
    }
}