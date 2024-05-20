package gymlife.utility.minigame;

import gymlife.model.minigame.BenchMinigame;
import gymlife.model.minigame.LatMachineMinigame;
import gymlife.model.minigame.SquatMinigame;
import gymlife.model.statistics.StatsType;
import gymlife.view.minigame.LatMachineView;
import gymlife.view.minigame.SquatView;
import gymlife.view.minigame.BenchView;

/**
 * The MinigameType enum represents the different types of minigames available
 * in the gym life application.
 * Each minigame type is associated with a specific minigame class and view
 * class.
 */
public enum MinigameType {
    /**
     * Bench press minigame type.
     */
    BENCH_PRESS(
            BenchMinigame.class.getCanonicalName(),
            BenchView.class.getCanonicalName(),
            StatsType.CHEST_MASS),
    /**
     * Squat minigame type.
     */
    SQUAT(
            SquatMinigame.class.getCanonicalName(),
            SquatView.class.getCanonicalName(),
            StatsType.LEG_MASS),
    /**
     * Lat machine minigame type.
     */
    LAT_MACHINE(
            LatMachineMinigame.class.getCanonicalName(),
            LatMachineView.class.getCanonicalName(),
            StatsType.BACK_MASS);

    private final String minigameType;
    private final String minigameViewType;
    private final StatsType statsType;

    /**
     * Constructs a MinigameType enum constant with the specified minigame type and
     * minigame view type.
     *
     * @param minigameType     the fully qualified name of the minigame class
     *                         associated with this minigame type
     * @param minigameViewType the fully qualified name of the minigame view class
     *                         associated with this minigame type
     * @param statsType        the stats type associated with this minigame type
     */
    MinigameType(final String minigameType, final String minigameViewType, final StatsType statsType) {
        this.minigameType = minigameType;
        this.minigameViewType = minigameViewType;
        this.statsType = statsType;
    }

    /**
     * Returns the name of the minigame type.
     *
     * @return the name of the minigame type
     */
    public String getName() {
        return this.minigameType;
    }

    /**
     * Returns the name of the minigame view type.
     *
     * @return the name of the minigame view type
     */
    public String getViewName() {
        return this.minigameViewType;
    }

    /**
     * Returns the stats type associated with the minigame type.
     *
     * @return the stats type associated with the minigame type
     */
    public StatsType getStatsType() {
        return this.statsType;
    }
}
