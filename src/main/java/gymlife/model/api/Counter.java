package gymlife.model.api;

/**
 * The AbstractCounter class represents a counter with basic increment operations, decrement is optionally overridden.
 */
public class Counter {
    private int count;
    /**
     * Constructs a new AbstractCounter object with an initial count of 0.
     */
    public Counter() {
        count = 0;
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
    * Decrements the count by 1.
    */
    public void decrement() {
        count--;
        if (count < 0) {
            count = 0;
        }
    }
    /**
    * Increments or Decrements the count by specified int.
    */
    public void multiIncrement(final int value) {
        count += value;
        if (count < 0) {
            count = 0;
        }
    }
    /**
    * Reset count to 0.
    */
    public void resetCount() {
        count = 0;
    }
}
