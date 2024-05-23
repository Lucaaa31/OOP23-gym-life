package gymlife.model.statistics;

/**
 * The Counter class represents a counter with basic increment and decrement operations.
 */
public class CounterImpl implements gymlife.model.statistics.api.Counter {
    private int count;
    /**
     * Constructs a new Counter object with an initial count of the specified value.
     *
     * @param count the initial count value
     */
    public CounterImpl(final int count) {
        this.count = count;
    }
    /**
     * Constructs a new Counter object with an initial count of 0.
     */
    public CounterImpl() {
        this.count = 0;
    }
    /**
     * Returns the current count.
     *
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
        count++;
    }
    /**
     * Decrements the count by 1. If the count becomes negative, it is set to 0.
     */
    @Override
    public void decrement() {
        count--;
        if (count < 0) {
            count = 0;
        }
    }
    /**
     * Increments or decrements the count by the specified value.
     *
     * @param value the value to increment or decrement the count by
     */
    @Override
    public void multiIncrement(final int value) {
        count += value;
        if (count < 0) {
            count = 0;
        }
    }
    /**
     * Resets the count to 0.
     */
    @Override
    public void resetCount() {
        count = 0;
    }
    /**
     * Sets the count to the specified amount.
     *
     * @param amount the new count value
     */
    @Override
    public void setCount(final int amount) {
        count = amount;
    }
}
