package gymlife.model.api;

import java.util.Map;
import gymlife.utility.StatsType;

/**
 * The StatsManager interface provides methods to retrieve statistics related to gym life.
 */
public interface StatsManager {
    /**
     * Returns a map of statistics, where the key is the type of statistic and the value is the corresponding value.
     *
     * @return a map of statistics
     */
    Map<StatsType, Counter> getStats();
    int getDays();
    boolean isGameOver();
    boolean checkWin();
}
