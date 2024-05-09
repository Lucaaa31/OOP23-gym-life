package gymlife.model.api;

import java.util.Map;
import gymlife.utility.StatsType;

/**
 * The StatsManager interface provides methods to retrieve statistics related to gym life.
 */
public interface StatsManager {
    /**
     * Returns a map of statistics, where the key is the type of statistic and the value is the corresponding value.
     *
     * @return a map of statistics
     */
    Map<StatsType, Counter> getStats();
    /**
     * Returns the number of days in the gym life.
     *
     * @return the number of days
     */
    int getDays();
    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();
    /**
     * Checks if the player has won the game.
     *
     * @return true if the player has won, false otherwise
     */
    boolean checkWin();
    /**
     * Reset all the stats to the starting value. 
     * 
    */
    void resetAll();

    /**
     * Decrements the days count.
     */
    void newDay();
}