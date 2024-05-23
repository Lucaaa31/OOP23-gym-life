package gymlife.model.statistics.api;

import java.util.Map;

import gymlife.model.encounter.Encounter;
import gymlife.model.statistics.CounterImpl;
import gymlife.model.statistics.LimitedCounterImpl;
import gymlife.model.statistics.StatsType;

import javax.annotation.concurrent.Immutable;

/**
 * The StatsManager interface provides methods to retrieve statistics related to gym life.
 */
@Immutable
public interface StatsManager {
    /**
     * Returns a map of statistics, where the key is the type of statistic and the value is the corresponding value.
     *
     * @return a map of statistics
     */
    Map<StatsType, LimitedCounterImpl> getStats();
    /**
     * Returns the number of days in the gym life.
     *
     * @return the number of days
     */
    CounterImpl getDays();
    /**
     * Decrement the number of day of one.
     *
     */
    void newDay();
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
     * Modify the stats according to the encounter type, and the accept case of the specific encounter. 
     * @param encounter the encounter to deny
    */
    void acceptEncounter(Encounter encounter);
    /**
     * Modify the stats according to the encounter type, and the deny case of the specific encounter.
     * @param encounter the encounter to deny
    */
    void denyEncounter(Encounter encounter);
    /**
     * Retrieves the money of the game.
     *
     * @return the money of the game
     */
    CounterImpl getMoney();
    /**
     * Multincrement a specified stats to the value.
     *
     * @param stats of the game
     * @param value to set the stat
     */
    void multiIncrementStat(StatsType stats, int value);
    /**
     * Set a specified stats to the value.
     *
     * @param stats of the game
     * @param value to set the stat
     */
    void setStat(StatsType stats, int value);
}
