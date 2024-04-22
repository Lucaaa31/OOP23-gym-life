package gymlife.model;

import java.util.HashMap;
import java.util.Map;


import gymlife.model.api.Counter;
import gymlife.model.api.StatsModel;
import gymlife.utility.StatsConstants;
import gymlife.utility.StatsType;

/**
 * Implementation of the StatsModel interface that represents the statistics of a gym member.
 */
public final class StatsModelImpl implements StatsModel {
    private Map<StatsType,Counter> gameStats = new HashMap<>();
    private static Counter humor = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter stamina = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter legMass = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter backMass = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    private static Counter chestMass = new Counter(StatsConstants.STARTING_STATS_LEVEL);
    /**
     * Returns the total mass value of the gym member.
     *
     * @return the total mass value
     */
    public StatsModelImpl() {
        gameStats.put(StatsType.BACK_MASS, backMass);
        gameStats.put(StatsType.CHEST_MASS, chestMass);
        gameStats.put(StatsType.LEG_MASS, legMass);
        gameStats.put(StatsType.STAMINA, stamina);
        gameStats.put(StatsType.HUMOR, humor);
    }
    @Override
    public void increase(final StatsType stats) {
        if (gameStats.get(stats).equals(StatsType.MASS)) {
            gameStats.get(StatsType.BACK_MASS).increment();
            gameStats.get(StatsType.CHEST_MASS).increment();
            gameStats.get(StatsType.LEG_MASS).increment();
        }
        else {
            gameStats.get(stats).increment();
        }
    }
    @Override
    public void decrease(final StatsType stats) {
        if (gameStats.get(stats).equals(StatsType.MASS)) {
            gameStats.get(StatsType.BACK_MASS).decrement();
            gameStats.get(StatsType.CHEST_MASS).decrement();
            gameStats.get(StatsType.LEG_MASS).decrement();
        }
        else {
            gameStats.get(stats).decrement();
        }   
    }

    @Override
    public int getStats(final StatsType stats) {
        if (gameStats.get(stats).equals(StatsType.MASS)) {
            return gameStats.get(StatsType.BACK_MASS).getCount() 
            + gameStats.get(StatsType.CHEST_MASS).getCount() 
            + gameStats.get(StatsType.LEG_MASS).getCount();
        }
        else {
            return gameStats.get(stats).getCount();
        }
    }
    
    @Override
    public Map<StatsType, Counter> getMap() {
        return gameStats;
    }

    @Override
    public void multiIncrement(final StatsType stats, final int value) {
        if (gameStats.get(stats).equals(StatsType.MASS)) {
            gameStats.get(StatsType.BACK_MASS).multiIncrement(value);
            gameStats.get(StatsType.CHEST_MASS).multiIncrement(value);
            gameStats.get(StatsType.LEG_MASS).multiIncrement(value);
        }
        else {
            gameStats.get(stats).multiIncrement(value);
        }
    }

    @Override
    public void resetAll() {
        for (StatsType stats : gameStats.keySet()) {
            gameStats.get(stats).multiIncrement(StatsConstants.STARTING_STATS_LEVEL);
        }
    }
    @Override
    public int getMass() {
        return gameStats.get(StatsType.BACK_MASS).getCount() + 
        gameStats.get(StatsType.CHEST_MASS).getCount() +
        gameStats.get(StatsType.LEG_MASS).getCount();
    }
}
