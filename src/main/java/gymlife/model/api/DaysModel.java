package gymlife.model.api;

/**
 * The DaysModel interface represents a model for tracking days.
 */
public interface DaysModel {
    /**
     * Checks if the days are over.
     * 
     * @return true if the days are over, false otherwise
     */
    boolean isDayOver();
    /**
     * Moves to a new day.
     */
    void newDay();
    /**
     * Gets the number of days left.
     * 
     * @return the number of days left
     */
    int dayLeft();
}
