package gymlife.model.statistics;

import java.util.Map;

import gymlife.model.encounter.Encounter;
import gymlife.model.statistics.api.StatsManager;
import gymlife.model.statistics.api.CommonStats;
import gymlife.utility.GameDifficulty;
/**
 * Implementation of the StatsManager interface.
 * This class manages the game statistics and provides methods to retrieve the stats.
 * This class is also responsible for hanling stats variations and the logic of that change.
 */
public class StatsManagerImpl implements StatsManager {
    private final CommonStats gameStats;
    private final GameCounterImpl gameDays;
    private final GameCounterImpl gameMoney;
    /**
     * Constructs a StatsManagerImpl object with the given game difficulty.
     * Initializes the gameStats and gameDays objects.
     * 
     * @param difficulty the game difficulty
     */
    public StatsManagerImpl(final GameDifficulty difficulty) {
        gameStats = new CommonStatsImpl();
        gameDays = new GameCounterImpl(difficulty.getDays());
        gameMoney = new GameCounterImpl(StatsConstants.STARTING_STATS_LEVEL);
    }
    /**
     * Retrieves the game statistics as a map of StatsType and their corresponding values.
     * 
     * @return a map of StatsType and their corresponding values
     */
    @Override
    public Map<StatsType, LimitedGameCounterImpl> getCommonStats() {
        return gameStats.getMap();
    }
    /**
     * Retrieves the money of the game.
     *
     * @return the money of the game
     */
    @Override
    public int getMoney() {
        return gameMoney.getCount();
    }
    /**
     * Multincrement a specified stats to the value.
     *
     * @param stats of the game
     * @param value to set the stat
     */
    @Override
    public void multiIncrementStat(final StatsType stats, final int value) {
        if (stats.equals(StatsType.MONEY)) {
            gameMoney.multiIncrement(value);
        } else {
            gameStats.multiIncrementStats(stats, value);
        }
    }

    /**
     * Multincrement a Map of stats.
     *
     * @param stats to be changed
     */
    @Override
    public void changeStatsWithFood(final Map<StatsType, Integer> stats) {
        for (final Map.Entry<StatsType, Integer> entry : stats.entrySet()) {
            gameStats.multiIncrementStats(entry.getKey(), entry.getValue());
        }
    }
    /**
     * Set a specified stats to the value.
     *
     * @param stats of the game
     * @param value to set the stat
     */
    @Override
    public void setStat(final StatsType stats, final int value) {
        if ("MONEY".equals(stats.toString())) {
            gameMoney.setCount(value);
        } else {
            gameStats.setStats(stats, value);
        }
    }
    /**
     * Retrieves the number of days left in the game.
     * 
     * @return the number of days left
     */
    @Override
    public int getDays() {
        return gameDays.getCount();
    }
    /**
     * Increments the number of days by one.
     * 
     */
    @Override
    public void newDay() {
        gameDays.decrement();
    }
    /**
     * Checks if the game is over.
     * The game is considered over if either one of the stats is zero or the days are over.
     * 
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        if (gameDays.getCount() == 0) {
            return true;
        }
        return gameStats.getMap().values().stream()
                .anyMatch(counter -> counter.getCount() == 0);
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
                gameMoney.multiIncrement(entry.getValue());
            } else {
                gameStats.multiIncrementStats(entry.getKey(), entry.getValue());
            }
        }
    }
    /**
     * Modifies the game statistics according to the encounter type and the deny case of the specific encounter.
     * 
     * @param encounterImpl the encounter to deny
     */
    @Override
    public void denyEncounter(final Encounter encounterImpl) {
        final Map<StatsType, Integer> denyCase = encounterImpl.getDenyCase();
        for (final Map.Entry<StatsType, Integer> entry : denyCase.entrySet()) {
            if (entry.getKey() == StatsType.MONEY) {
                gameMoney.multiIncrement(entry.getValue());
            } else {
                gameStats.multiIncrementStats(entry.getKey(), entry.getValue());
            }
        }
    }
}
