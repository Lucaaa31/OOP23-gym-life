package gymlife.model.statistics;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.statistics.api.StatsModel;

/**
 * Implementation of the StatsModel interface that represents the statistics of a gym member.
 */
public final class StatsModelImpl implements StatsModel {
    private final Map<StatsType, LimitedCounterImpl> gameStats = new HashMap<>();
    private final LimitedCounterImpl legMass = new LimitedCounterImpl(StatsConstants.STARTING_STATS_LEVEL,
            StatsConstants.MAX_STATS_LEVEL);
    private final LimitedCounterImpl backMass = new LimitedCounterImpl(StatsConstants.STARTING_STATS_LEVEL,
            StatsConstants.MAX_STATS_LEVEL);
    private final LimitedCounterImpl chestMass = new LimitedCounterImpl(StatsConstants.STARTING_STATS_LEVEL,
            StatsConstants.MAX_STATS_LEVEL);

    /**
     * Constructs a new StatsModelImpl object.
     * Initializes the gameStats map with the initial values for each stat.
     */
    public StatsModelImpl() {
        gameStats.put(StatsType.BACK_MASS, backMass);
        gameStats.put(StatsType.CHEST_MASS, chestMass);
        gameStats.put(StatsType.LEG_MASS, legMass);
        final LimitedCounterImpl mass = new LimitedCounterImpl(StatsConstants.STARTING_MASS_LEVEL,
                StatsConstants.MAX_MASS_LEVEL) {
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
            public void resetCount() {
                backMass.resetCount();
                chestMass.resetCount();
                legMass.resetCount();
            }
            @Override
            public void setCount(final int count) {
                backMass.setCount(count);
                chestMass.setCount(count);
                legMass.setCount(count);
            }
            @Override
            public int getCount() {
                return backMass.getCount() + chestMass.getCount() + legMass.getCount();
            }
        };
        gameStats.put(StatsType.MASS, mass);
        final LimitedCounterImpl stamina = new LimitedCounterImpl(StatsConstants.MAX_STATS_LEVEL,
                StatsConstants.MAX_STATS_LEVEL);
        gameStats.put(StatsType.STAMINA, stamina);
        final LimitedCounterImpl happiness = new LimitedCounterImpl(StatsConstants.MAX_STATS_LEVEL,
                StatsConstants.MAX_STATS_LEVEL);
        gameStats.put(StatsType.HAPPINESS, happiness);
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
    public Map<StatsType, LimitedCounterImpl> getMap() {
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
        for (final Map.Entry<StatsType, LimitedCounterImpl> entry : gameStats.entrySet()) {
            entry.getValue().resetCount();
        }
    }

    /**
     * Reset all stats to 0.
     *
     * @param stats the stat to reset
     * @param value the value to reset the stat to
     */
    @Override
    public void setStats(final StatsType stats, final int value) {
        gameStats.get(stats).setCount(value);
    }
}
