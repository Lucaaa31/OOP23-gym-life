package gymlife.model;

import java.util.Map;

import gymlife.model.api.StatsModel;
import gymlife.model.api.Counter;
import gymlife.model.api.DaysModel;
import gymlife.utility.GameDifficulty;
import gymlife.utility.StatsType;
import gymlife.model.api.StatsManager;

/**
 * Implementation of the StatsManager interface.
 * This class manages the game statistics and provides methods to retrieve the stats.
 */
public class StatsManagerImpl implements StatsManager {
    private final StatsModel gameStats;
    private final DaysModel gameDays;
    /**
     * Constructs a StatsManagerImpl object with the given game difficulty.
     * Initializes the gameStats and gameDays objects.
     * 
     * @param difficulty the game difficulty
     */
    public StatsManagerImpl(final GameDifficulty difficulty) {
        gameStats = new StatsModelImpl();
        gameDays = new DaysModelImpl(difficulty.getDays());
    }
    /**
     * Retrieves the game statistics as a map of StatsType and their corresponding values.
     * 
     * @return a map of StatsType and their corresponding values
     */
    @Override
    public Map<StatsType, Counter> getStats() {
        return gameStats.getMap();
    }
    /**
     * Retrieves the game days as a map of DaysType and their corresponding values.
     * 
     * @return a map of DaysType and their corresponding values
     */
    public int getDays() {
        return gameDays.dayLeft();
    }
    @Override
    public boolean isGameOver() {
        return gameDays.isDayOver();
    }
    
}
