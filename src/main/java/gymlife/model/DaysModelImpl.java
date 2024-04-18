package gymlife.model;

import gymlife.model.api.Counter;
import gymlife.model.api.DaysModel;

/**
 * Implementation of the DaysModel interface.
 */
public class DaysModelImpl extends Counter implements DaysModel {

    private final int numDays;

    /**
     * Constructs a new DaysModelImpl object with the specified number of days.
     *
     * @param numDays the number of days for the model
     */
    public DaysModelImpl(final int numDays) {
        this.numDays = numDays;
    }
    /**
     * Checks if the days are over.
     *
     * @return true if the days are over, false otherwise
     */
    @Override
    public boolean daysAreOver() {
        return this.getCount() < numDays;
    }
    /**
     * Increases the number of days by 1.
     */
    @Override
    public void newDay() {
        this.increment();
    }
    /**
     * Returns the number of days left.
     *
     * @return the number of days left
     */
    @Override
    public int dayLeft() {
        return numDays - this.getCount();
    }
}
