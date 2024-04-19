package gymlife.model;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.api.Counter;
import gymlife.model.api.StatsModel;
import gymlife.utility.StatsType;

/**
 * Implementation of the StatsModel interface that represents the statistics of a gym member.
 */
public final class StatsModelImpl implements StatsModel {
    private Map<StatsType,Counter> gameStats = new HashMap<>();
    private static Counter humor = new Counter();
    private static Counter stamina = new Counter();
    private static Counter legMass = new Counter();
    private static Counter backMass = new Counter();
    private static Counter chestMass = new Counter();
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
    public void increase(StatsType stats) {
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
    public void decrease(StatsType stats) {
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
    public int getStats(StatsType stats) {
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
    public void multiIncrement(StatsType stats, int value) {
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
        gameStats.get(StatsType.BACK_MASS).resetCount();
        gameStats.get(StatsType.CHEST_MASS).resetCount();
        gameStats.get(StatsType.LEG_MASS).resetCount();
        gameStats.get(StatsType.STAMINA).resetCount();
        gameStats.get(StatsType.HUMOR).resetCount();
    }
}
