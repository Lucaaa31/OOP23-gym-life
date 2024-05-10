package gymlife.model.statistics;

/**
 * The Counter class represents a counter with basic increment and decrement operations.
 */
public class Counter {
    private int count;
    /**
     * Constructs a new Counter object with an initial count of the specified value.
     *
     * @param count the initial count value
     */
    public Counter(final int count) {
        this.count = count;
    }
    /**
     * Constructs a new Counter object with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getCount() {
        return this.count;
    }
    /**
     * Increments the count by 1.
     */
    public void increment() {
        count++;
    }
    /**
     * Decrements the count by 1. If the count becomes negative, it is set to 0.
     */
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
    public void multiIncrement(final int value) {
        count += value;
        if (count < 0) {
            count = 0;
        }
    }
    /**
     * Resets the count to 0.
     */
    public void resetCount() {
        count = 0;
    }
    /**
     * Sets the count to the specified amount.
     *
     * @param amount the new count value
     */
    public void setCount(final int amount) {
        count = amount;
    }
}
