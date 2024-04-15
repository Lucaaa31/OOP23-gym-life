package gymlife.model;

import gymlife.model.api.AbstractCounter;
import gymlife.model.api.DaysModel;
import gymlife.utility.Constants;

/**
 * Implementation of the DaysModel interface.
 */
public class DaysModelImpl extends AbstractCounter implements DaysModel {



    /**
     * Checks if the days are over.
     *
     * @return true if the days are over, false otherwise.
     */
    @Override
    public boolean daysAreOver() {
        return this.count < Constants.DAYS_MAX_COUNT;
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
        return Constants.DAYS_MAX_COUNT - this.count;
    }
}

