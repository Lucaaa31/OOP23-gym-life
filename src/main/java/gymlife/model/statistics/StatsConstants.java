package gymlife.model.statistics;

/**
 * Constants class contains various constants used in the stats.
 */
public final class StatsConstants {
    /**
     * The starting level of the character's stats.
     */
    public static final int STARTING_STATS_LEVEL = 1;
    /**
     * The starting level of the character's stats.
     */
    public static final int STARTING_MASS_LEVEL = STARTING_STATS_LEVEL * 3;
    /**
    /**
     * The maximum level of the character's stats.
     */
    public static final int MAX_STATS_LEVEL = 100;
    /**
     * The maximum level of the character's stats.
     */
    public static final int MAX_MASS_LEVEL = 3 * MAX_STATS_LEVEL;
    /**
     * One third of the total amount of a stats .
     */
    public static final int LEVEL_1 = MAX_STATS_LEVEL / 3;
    /**
     * Two thirds of the total amount of a stats .
     */
    public static final int LEVEL_2 = MAX_STATS_LEVEL / 3 * 2;

    private StatsConstants() {
    }
}
