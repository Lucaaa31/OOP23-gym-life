package gymlife.model.statistics.api;

/**
 * The Counter class represents a counter with basic increment and decrement operations.
 */
public interface Counter {
    /**
     * Returns the current count.
     *
     * @return the current count
     */
    int getCount();

    /**
     * Increments the count by 1.
     */
    void increment();

    /**
     * Decrements the count by 1. If the count becomes negative, it is set to 0.
     */
    void decrement();

    /**
     * Increments or decrements the count by the specified value.
     *
     * @param value the value to increment or decrement the count by
     */
    void multiIncrement(int value);

    /**
     * Resets the count to 0.
     */
    void resetCount();

    /**
     * Sets the count to the specified amount.
     *
     * @param amount the new count value
     */
    void setCount(int amount);
}
