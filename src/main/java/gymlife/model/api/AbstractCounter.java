package gymlife.model.api;

/**
 * The AbstractCounter class represents a counter with basic increment operations, decrement in optionally overridden.
 */
public abstract class AbstractCounter {
    protected int count = 0;
    
    /**
     * Constructs a new AbstractCounter object with an initial count of 0.
     */
    public AbstractCounter() {
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
}
