package gymlife.model;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.api.StatsModel;
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
    public Map<StatsType, Integer> getStats() {
        final Map<StatsType, Integer> stats = new HashMap<>();
        stats.put(StatsType.BACK_MASS, gameStats.getBackMass());
        stats.put(StatsType.CHEST_MASS, gameStats.getChestMass());
        stats.put(StatsType.LEG_MASS, gameStats.getLegMass());
        stats.put(StatsType.MASS, gameStats.getStamina());
        stats.put(StatsType.STAMINA, gameStats.getStamina());
        stats.put(StatsType.HUMOR, gameStats.getHumor());
        stats.put(StatsType.DAYS_LEFT, gameDays.dayLeft());
        return stats;
    }
}
