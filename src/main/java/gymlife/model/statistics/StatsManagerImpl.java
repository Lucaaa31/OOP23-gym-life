package gymlife.model.statistics;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.encounter.Encounter;
import gymlife.model.statistics.api.StatsManager;
import gymlife.model.statistics.api.DaysModel;
import gymlife.model.statistics.api.MoneyModel;
import gymlife.model.statistics.api.StatsModel;
import gymlife.utility.GameDifficulty;
/**
 * Implementation of the StatsManager interface.
 * This class manages the game statistics and provides methods to retrieve the stats.
 */
public class StatsManagerImpl implements StatsManager {
    private final StatsModel gameStats;
    private final DaysModel gameDays;
    private final MoneyModel gameMoney;
    /**
     * Constructs a StatsManagerImpl object with the given game difficulty.
     * Initializes the gameStats and gameDays objects.
     * 
     * @param difficulty the game difficulty
     */
    public StatsManagerImpl(final GameDifficulty difficulty) {
        gameStats = new StatsModelImpl();
        gameDays = new DaysModelImpl(difficulty.getDays());
        gameMoney = new MoneyModelImpl(StatsConstants.STARTING_STATS_LEVEL);
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
     * Retrieves the all game statistics as a map of StatsType and their corresponding values,
     * including the money and days.
     *
     * @return a map of StatsType and their corresponding values
     */
    @Override
    public Map<StatsType, Counter> getAllStats() {
        final Map<StatsType, Counter> statsMap = new HashMap<>(this.getStats());
        statsMap.put(StatsType.MONEY, this.getMoney());
        statsMap.put(StatsType.DAYS, this.getDays());
        return Map.copyOf(statsMap);
    }

    /**
     * Retrieves the money of the game.
     *
     * @return the money of the game
     */
    @Override
    public Counter getMoney() {
        return new Counter(gameMoney.getMoney());
    }

    /**
     * Retrieves the number of days left in the game.
     * 
     * @return the number of days left
     */
    @Override
    public Counter getDays() {
        return new Counter(gameDays.dayLeft());
    }
    /**
     * Increments the number of days by one.
     * 
     */
    @Override
    public void newDay() {
        gameDays.newDay();
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
        for (final Map.Entry<StatsType, Counter> entry : statsMap.entrySet()) {
            if (entry.getValue().getCount() == 0) {
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
        return gameStats.getStats(StatsType.MASS) >= StatsConstants.MAX_MASS_LEVEL;
    }
    /**
     * Resets all the game statistics to their initial values.
     */
    @Override
    public void resetAll() {
        gameStats.resetAll();
    }
    /**
     * Modifies the game statistics according to the encounter type and the accept case of the specific encounter.
     * 
     * @param encounter the encounter to accept
     */
    @Override
    public void acceptEncounter(final Encounter encounter) {
        final Map<StatsType, Integer> acceptCase = encounter.acceptCase();
        for (final Map.Entry<StatsType, Integer> entry : acceptCase.entrySet()) {
            if (entry.getKey() == StatsType.MONEY) {
                gameMoney.multiIncrementMoney(entry.getValue());
            } else {
                gameStats.multiIncrementStats(entry.getKey(), entry.getValue());
            }
        }
    }
    /**
     * Modifies the game statistics according to the encounter type and the deny case of the specific encounter.
     * 
     * @param encounter the encounter to deny
     */
    @Override
    public void denyEncounter(final Encounter encounter) {
        final Map<StatsType, Integer> denyCase = encounter.denyCase();
        for (final Map.Entry<StatsType, Integer> entry : denyCase.entrySet()) {
            if (entry.getKey() == StatsType.MONEY) {
                gameMoney.multiIncrementMoney(entry.getValue());
            } else {
                gameStats.multiIncrementStats(entry.getKey(), entry.getValue());
            }
        }
    }
}
