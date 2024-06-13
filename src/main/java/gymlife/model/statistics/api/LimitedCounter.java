package gymlife.model.statistics.api;

/**
 * A class that represents a counter with a limit.
 * This counter can be incremented and decremented, but it cannot exceed its limit or go below zero.
 * It extends the Counter class.
 */
public interface LimitedCounter {
    /**
     * Increments the counter by one, but only if the current count is less than the limit.
     */
    void increment();

    /**
     * Decrements the counter by one, but only if the current count is greater than zero.
     */
    void decrement();

    /**
     * Increments the counter by a specified value, but ensures that the count does not exceed the limit or go below zero.
     *
     * @param value The value to increment the counter by.
     */
    void multiIncrement(int value);

    /**
     * Checks if the counter has reached its limit.
     *
     * @return true if the counter is at its limit, false otherwise.
     */
    boolean isMax();

    /**
     * Resets the counter to starting stats level.
     */
    void resetCount();
}
