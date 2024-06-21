package gymlife.model.statistics;

import gymlife.model.statistics.api.LimitedCounter;
/**
 * A class that represents a counter with a limit.
 * This counter can be incremented and decremented, but it cannot exceed its limit or go below zero.
 * It extends the Counter class.
 */
public class LimitedGameCounterImpl extends GameCounterImpl implements LimitedCounter {
    // The maximum value that this counter can reach.
    private final int limit;

    /**
     * Constructs a new LimitedCounter with a specified count and limit.
     *
     * @param count The initial count.
     * @param limit The maximum value that this counter can reach.
     */
    public LimitedGameCounterImpl(final int count, final int limit) {
        super(count);
        this.limit = limit;
    }

    /**
     * Constructs a new LimitedCounter with a specified limit and a count of zero.
     *
     * @param limit The maximum value that this counter can reach.
     */
    public LimitedGameCounterImpl(final int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Increments the counter by one, but only if the current count is less than the limit.
     */
    @Override
    public void increment() {
        if (super.getCount() < limit) {
            super.increment();
        }
    }

    /**
     * Decrements the counter by one, but only if the current count is greater than zero.
     */
    @Override
    public void decrement() {
        if (super.getCount() > 0) {
            super.decrement();
        }
    }

    /**
     * Increments the counter by a specified value, but ensures that the count does not exceed the limit or go below zero.
     *
     * @param value The value to increment the counter by.
     */
    @Override
    public void multiIncrement(final int value) {
        super.multiIncrement(value);
        if (this.isMax()) {
            setCount(limit);
        }
    }
    /**
     * Sets the count to the specified amount.
     *
     * @param count the new count value
     */
    @Override
    public void setCount(final int count) {
        super.setCount(count);
        if (this.isMax()) {
            super.setCount(limit);
        } else if (super.getCount() <= 0) {
            resetCount();
        }
    }

    /**
     * Checks if the counter has reached its limit.
     *
     * @return true if the counter is at its limit, false otherwise.
     */
    @Override
    public boolean isMax() {
        return limit <= super.getCount();
    }
}
