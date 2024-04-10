package gymlife.model;

import gymlife.model.api.DaysModel;
import gymlife.utility.Constants;

/**
 * Implementation of the DaysModel interface.
 */
public class DaysModelImpl implements DaysModel {

    private static int days = Constants.DAYS_STARTER_COUNT;

    /**
     * Checks if the days are over.
     *
     * @return true if the days are over, false otherwise.
     */
    @Override
    public boolean daysAreOver() {
        return false;
    }

    /**
     * Decreases the number of days by 1.
     */
    @Override
    public void newDay() {
        days = days - 1;
    }

    /**
     * Returns the number of days left.
     *
     * @return the number of days left.
     */
    @Override
    public int dayLeft() {
        return days;
    }
}

