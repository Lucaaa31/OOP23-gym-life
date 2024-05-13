package gymlife.utility.minigame;

import gymlife.model.minigame.BenchMinigame;
import gymlife.model.minigame.LatMachineMinigame;
import gymlife.model.minigame.SquatMinigame;
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
            BenchView.class.getCanonicalName()),
    /**
     * Squat minigame type.
     */
    SQUAT(
            SquatMinigame.class.getCanonicalName(),
            SquatView.class.getCanonicalName()),
    /**
     * Lat machine minigame type.
     */
    LAT_MACHINE(
            LatMachineMinigame.class.getCanonicalName(),
            LatMachineView.class.getCanonicalName());

    private final String minigameType;
    private final String minigameViewType;

    /**
     * Constructs a MinigameType enum constant with the specified minigame type and
     * minigame view type.
     *
     * @param minigameType     the fully qualified name of the minigame class
     *                         associated with this minigame type
     * @param minigameViewType the fully qualified name of the minigame view class
     *                         associated with this minigame type
     */
    MinigameType(final String minigameType, final String minigameViewType) {
        this.minigameType = minigameType;
        this.minigameViewType = minigameViewType;
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
}
