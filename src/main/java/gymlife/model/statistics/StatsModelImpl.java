package gymlife.model.statistics;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.statistics.api.StatsModel;

/**
 * Implementation of the StatsModel interface that represents the statistics of a gym member.
 */
public final class StatsModelImpl implements StatsModel {
    private final Map<StatsType, Counter> gameStats = new HashMap<>();
    private static Counter humor = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter stamina = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter legMass = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter backMass = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter chestMass = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter mass = new Counter(StatsConstants.STARTING_STATS_LEVEL) {
        @Override
        public void resetCount() {
            backMass.resetCount();
            chestMass.resetCount();
            legMass.resetCount();
        }
        @Override
        public void setCount(final int value) {
            backMass.setCount(value);
            chestMass.setCount(value);
            legMass.setCount(value);
        }
        @Override
        public void increment() {
            backMass.increment();
            chestMass.increment();
            legMass.increment();
        }
        @Override
        public void decrement() {
            backMass.decrement();
            chestMass.decrement();
            legMass.decrement();
        }
        @Override
        public void multiIncrement(final int value) {
            backMass.multiIncrement(value);
            chestMass.multiIncrement(value);
            legMass.multiIncrement(value);
        }
        @Override
        public int getCount() {
            return backMass.getCount() + chestMass.getCount() + legMass.getCount();
        }
    };
    /**
     * Constructs a new StatsModelImpl object.
     * Initializes the gameStats map with the initial values for each stat.
     */
    public StatsModelImpl() {
        gameStats.put(StatsType.BACK_MASS, backMass);
        gameStats.put(StatsType.CHEST_MASS, chestMass);
        gameStats.put(StatsType.LEG_MASS, legMass);
        gameStats.put(StatsType.MASS, mass);
        gameStats.put(StatsType.STAMINA, stamina);
        gameStats.put(StatsType.HUMOR, humor);
    }
    /**
     * Increases the specified stat by 1.
     * If the stat is MASS, it increases all the individual mass stats (back, chest, leg) by 1.
     * 
     * @param stats the stat to increase
     */
    @Override
    public void increase(final StatsType stats) {
        gameStats.get(stats).increment();
    }
    /**
     * Decreases the specified stat by 1.
     * If the stat is MASS, it decreases all the individual mass stats (back, chest, leg) by 1.
     * 
     * @param stats the stat to decrease
     */
    @Override
    public void decrease(final StatsType stats) {
        gameStats.get(stats).decrement();
    }
    /**
     * Returns the value of the specified stat.
     * If the stat is MASS, it returns the sum of all the individual mass stats (back, chest, leg).
     * 
     * @param stats the stat to get the value of
     * @return the value of the specified stat
     */
    @Override
    public int getStats(final StatsType stats) {
        return gameStats.get(stats).getCount();
    }
    /**
     * Returns the map of all the game stats.
     * 
     * @return the map of all the game stats
     */
    @Override
    public Map<StatsType, Counter> getMap() {
        return Map.copyOf(gameStats);
    }
    /**
     * Increases the specified stat by the specified value.
     * If the stat is MASS, it increases all the individual mass stats (back, chest, leg) by the specified value.
     * 
     * @param stats the stat to increase
     * @param value the value to increase the stat by
     */
    @Override
    public void multiIncrementStats(final StatsType stats, final int value) {
        gameStats.get(stats).multiIncrement(value);
    }
    /**
     * Resets all the game stats to STARTING_STATS_LEVEL.
     */
    @Override
    public void resetAll() {
        for (final Map.Entry<StatsType, Counter> entry : gameStats.entrySet()) {
            entry.getValue().setCount(StatsConstants.STARTING_STATS_LEVEL);
        }
    }
}
