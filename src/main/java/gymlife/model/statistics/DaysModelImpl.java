package gymlife.model.statistics;

import gymlife.model.statistics.api.DaysModel;

/**
 * Implementation of the DaysModel interface.
 */
public class DaysModelImpl extends CounterImpl implements DaysModel {
    /**
     * Constructs a new DaysModelImpl object with the specified number of days.
     *
     * @param numDays the number of days for the model
     */
    public DaysModelImpl(final int numDays) {
        super(numDays);
    }
    /**
     * Decrement the days count by one.
     */
    @Override
    public void newDay() {
        this.decrement();
    }
    /**
     * Returns the number of days left.
     *
     * @return the number of days left
     */
    @Override
    public int dayLeft() {
        return this.getCount();
    }
    /**
     * Checks if the days are over.
     *
     * @return true if the days are over, false otherwise
     */
    @Override
    public boolean isDayOver() {
        return this.getCount() == 0;
    }
}
