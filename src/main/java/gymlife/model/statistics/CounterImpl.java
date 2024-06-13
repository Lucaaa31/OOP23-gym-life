package gymlife.model.statistics;

/**
 * The CounterImpl class represents a counter with basic increment and decrement operations.
 * The counter can be incremented, decremented, and reset to 0, but it cannot go below 0.
 */
public class CounterImpl implements gymlife.model.statistics.api.Counter {
    private int count;

    /**
     * Constructs a new CounterImpl object with an initial count of the specified value.
     * If the specified count is negative, the count is set to 0.
     * @param count the initial count value
     */
    public CounterImpl(final int count) {
        this.count = Math.max(count, 0);
    }

    /**
     * Constructs a new CounterImpl object with an initial count of 0.
     * The count is set to 0.
     */
    public CounterImpl() {
        this(0);
    }

    /**
     * Returns the current count.
     * @return the current count
     */
    @Override
    public int getCount() {
        return this.count;
    }

    /**
     * Increments the count by 1.
     */
    @Override
    public void increment() {
        this.count++;
    }

    /**
     * Decrements the count by 1. If the count becomes negative, it is set to 0.
     */
    @Override
    public void decrement() {
        this.count = Math.max(this.count - 1, 0);
    }

    /**
     * Increments or decrements the count by the specified value.
     *
     * @param value the value to increment or decrement the count by
     */
    @Override
    public void multiIncrement(final int value) {
        this.count = Math.max(this.count + value, 0);
    }

    /**
     * Resets the count to 0.
     */
    @Override
    public void resetCount() {
        this.count = 0;
    }

    /**
     * Sets the count to the specified amount.
     *
     * @param amount the new count value
     */
    @Override
    public void setCount(final int amount) {
        this.count = Math.max(amount, 0);
    }
}
