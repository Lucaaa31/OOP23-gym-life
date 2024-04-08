package gymlife.model;

import gymlife.model.api.DaysModel;
import gymlife.utility.Constants;

public class DaysModelImpl implements DaysModel{
     
    private static int days = Constants.DAYS_STARTER_COUNT;

    @Override
    public boolean daysAreOver() {
        return false;
    }
    @Override
    public void newDay() {
        days = days-1;
    }
    @Override
    public int dayLeft() {
        return days;
    }
}
