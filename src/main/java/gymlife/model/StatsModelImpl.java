package gymlife.model;

import gymlife.model.api.AbstractCounter;
import gymlife.model.api.StatsModel;
import gymlife.utility.Constants;

/**
 * Implementation of the StatsModel interface that represents the statistics of a gym member.
 */
public final class StatsModelImpl implements StatsModel {
    private static AbstractCounter humor = new AbstractCounter() {};
    private static AbstractCounter stamina = new AbstractCounter() {};
    private static AbstractCounter legMass = new AbstractCounter() {};
    private static AbstractCounter backMass = new AbstractCounter() {};
    private static AbstractCounter chestMass = new AbstractCounter() {};

    /**
     * Returns the total mass value of the gym member.
     *
     * @return the total mass value
     */
    @Override
    public int getMass() {
        return chestMass.getCount() + backMass.getCount() + legMass.getCount();
    }

    /**
     * Returns the mass value of the gym member's chest.
     *
     * @return the chest mass value
     */
    public int getChestMass() {
        return chestMass.getCount();
    }

    /**
     * Returns the mass value of the gym member's back.
     *
     * @return the back mass value
     */
    public int getBackMass() {  
        return backMass.getCount(); 
    }

    /**
     * Returns the mass value of the gym member's legs.
     *
     * @return the leg mass value
     */
    public int getLegMass() {
        return legMass.getCount();
    }
    
    /**
     * Returns the stamina value of the gym member.
     *
     * @return the stamina value
     */
    @Override
    public int getStamina() {
        return stamina.getCount();
    }

    /**
     * Returns the humor value of the gym member.
     *
     * @return the humor value
     */
    @Override
    public int getHumor() {
        return humor.getCount();
    }

    /**
     * Increments the mass values of the gym member's chest, back, and legs equally.
     * Returns true if the mass increment hasn't reached its maximum level.
     *
     * @return true if the mass increment hasn't reached its maximum level, false otherwise
     */
    @Override
    public boolean incMass() {
        chestMass.increment();
        backMass.increment();
        legMass.increment();
        return chestMass.getCount() < Constants.MAX_STATS_LEVEL && backMass.getCount() < Constants.MAX_STATS_LEVEL && legMass.getCount() < Constants.MAX_STATS_LEVEL;
    }

    /**
     * Increments the stamina value of the gym member.
     * Returns true if the stamina increment hasn't reached its maximum level.
     *
     * @return true if the stamina increment hasn't reached its maximum level, false otherwise
     */
    @Override
    public boolean incStamina() {
        stamina.increment();
        return stamina.getCount() < Constants.MAX_STATS_LEVEL;
    }

    /**
     * Increments the humor value of the gym member.
     * Returns true if the humor increment hasn't reached its maximum level.
     *
     * @return true if the humor increment hasn't reached its maximum level, false otherwise
     */
    @Override
    public boolean inchumor() {
        humor.increment();
        return humor.getCount() < Constants.MAX_STATS_LEVEL;
    }

    /**
     * Increments the mass value of the gym member's chest.
     * Returns true if the chest mass increment hasn't reached its maximum level.
     *
     * @return true if the chest mass increment hasn't reached its maximum level, false otherwise
     */
    public boolean incChestMass() {
        chestMass.increment();
        return chestMass.getCount() < Constants.MAX_STATS_LEVEL;
    }

    /**
     * Increments the mass value of the gym member's back.
     * Returns true if the back mass increment hasn't reached its maximum level.
     *
     * @return true if the back mass increment hasn't reached its maximum level, false otherwise
     */
    public boolean incBackMass() {
        backMass.increment();
        return backMass.getCount() < Constants.MAX_STATS_LEVEL;
    }

    /**
     * Increments the mass value of the gym member's legs.
     * Returns true if the leg mass increment hasn't reached its maximum level.
     *
     * @return true if the leg mass increment hasn't reached its maximum level, false otherwise
     */
    public boolean incLegMass() {
        legMass.increment();
        return legMass.getCount() < Constants.MAX_STATS_LEVEL;
    }
}
