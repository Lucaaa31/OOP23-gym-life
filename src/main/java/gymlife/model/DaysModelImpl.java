package gymlife.model;

import gymlife.model.api.AbstractCounter;
import gymlife.model.api.DaysModel;

/**
 * Implementation of the DaysModel interface.
 */
public class DaysModelImpl extends AbstractCounter implements DaysModel {
    
    private final int numDays;

    public DaysModelImpl(final int numDays) {
        this.numDays = numDays;
    }  
    /**
     * Checks if the days are over.
     *
     * @return true if the days are over, false otherwise.
     */
    @Override
    public boolean daysAreOver() {
        return this.count < numDays;
    }

    /**
     * Increase the number of days by 1.
     */
    @Override
    public void newDay() {
        this.count++;
    }

    /**
     * Returns the number of days left.
     *
     * @return the number of days left.
     */
    @Override
    public int dayLeft() {
        return numDays - this.count;
    }
}

