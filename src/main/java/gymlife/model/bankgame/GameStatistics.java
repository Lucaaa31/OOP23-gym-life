package gymlife.model.bankgame;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tracks all the threshold in every bet.
 */
public final class GameStatistics {
    private final List<Float> list;

    /**
     * Constructs of GameStatistics object.
     */
    public GameStatistics() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a threshold value to the statistics.
     *
     * @param threshold The threshold value to add.
     */
    public void addThreshold(final float threshold) {
        list.add(threshold);
    }

    /**
     * Returns an immutable list of all recorded thresholds.
     *
     * @return A list containing all recorded thresholds.
     */
    public List<Float> getList() {
        return List.copyOf(list);
    }
}
