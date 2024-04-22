package gymlife.model;

import java.util.Map;

import gymlife.model.api.StatsModel;
import gymlife.model.api.Counter;
import gymlife.model.api.DaysModel;
import gymlife.utility.GameDifficulty;
import gymlife.utility.StatsConstants;
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
     * Retrieves the number of days left in the game.
     * 
     * @return the number of days left
     */
    public int getDays() {
        return gameDays.dayLeft();
    }    
    /**
     * Checks if the game is over.
     * The game is considered over if either one of the stats is zero or all the days are over.
     * 
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        if (gameDays.isDayOver()) {
            return true;
        }
        final Map<StatsType, Counter> statsMap = gameStats.getMap();
        for (final StatsType stats : statsMap.keySet()) {
            if (statsMap.get(stats).getCount() == 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the player has won the game.
     * The player is considered to have won if the total mass is equal to the maximum mass level.
     * 
     * @return true if the player has won, false otherwise
     */
    @Override
    public boolean checkWin() {
        return (gameStats.getStats(StatsType.MASS) >= StatsConstants.MAX_MASS_LEVEL ? true : false);
    }
}
