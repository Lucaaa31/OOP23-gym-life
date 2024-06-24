package gymlife.utility.minigame;

import gymlife.model.minigame.BenchStrategy;
import gymlife.model.minigame.LatStrategy;
import gymlife.model.minigame.SquatStrategy;
import gymlife.model.statistics.StatsType;
import gymlife.view.minigame.BenchView;
import gymlife.view.minigame.LatView;
import gymlife.view.minigame.SquatView;

import java.util.List;
import java.util.Objects;

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
            BenchStrategy.class.getCanonicalName(),
            BenchView.class.getCanonicalName(),
            StatsType.CHEST_MASS,
            "Bench Press",
            "Press the button as fast as you can!"
    ),
    /**
     * Squat minigame type.
     */
    SQUAT(
            SquatStrategy.class.getCanonicalName(),
            SquatView.class.getCanonicalName(),
            StatsType.LEG_MASS,
            "Squat",
            ""
    ),
    /**
     * Lat machine minigame type.
     */
    LAT_MACHINE(
            LatStrategy.class.getCanonicalName(),
            LatView.class.getCanonicalName(),
            StatsType.BACK_MASS,
            "Lat Machine",
            "Press the buttons in order!"
    );


    private final String minigameType;
    private final String minigameViewType;
    private final StatsType statsType;
    private final String name;
    private final String description;

    /**
     * Constructs a MinigameType enum constant with the specified minigame type and
     * minigame view type.
     *
     * @param minigameType     the fully qualified name of the minigame class
     *                         associated with this minigame type
     * @param minigameViewType the fully qualified name of the minigame view class
     *                         associated with this minigame type
     * @param statsType        the stats type associated with this minigame type
     * @param name             the name of the minigame type
     * @param description      the description of the minigame type
     */
    MinigameType(final String minigameType, final String minigameViewType, final StatsType statsType,
                 final String name, final String description) {
        this.minigameType = minigameType;
        this.minigameViewType = minigameViewType;
        this.statsType = statsType;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of the minigame type.
     *
     * @return the name of the minigame type
     */
    public String getStrategy() {
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

    /**
     * Returns the name of the minigame type.
     *
     * @return the name of the minigame type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the minigame type.
     *
     * @return the description of the minigame type
     */
    public String getDescription() {
        return description;
    }


}
