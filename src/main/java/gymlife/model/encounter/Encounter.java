package gymlife.model.encounter;

import gymlife.model.statistics.StatsType;
import java.util.Map;

/**
 * Class that represent a random encounter made from the player in the game.
 * @param name        the name of the encounter
 * @param getDescription the getDescription of the encounter
 * @param acceptCase  the map of stats type and corresponding values for accepting the encounter
 * @param getDenyCase    the map of stats type and corresponding values for denying the encounter
 */
public record Encounter(String name, String getDescription, Map<StatsType, Integer> acceptCase,
                        Map<StatsType, Integer> getDenyCase) {
    /**
     * Constructs an Encounters object with the given getDescription, accept case, and deny case.
     *
     * @param name        the name of the encounter
     * @param getDescription the getDescription of the encounter
     * @param acceptCase  the map of stats type and corresponding values for accepting the encounter
     * @param getDenyCase    the map of stats type and corresponding values for denying the encounter
     */
    public Encounter(final String name, final String getDescription, final Map<StatsType, Integer> acceptCase,
                     final Map<StatsType, Integer> getDenyCase) {
        this.name = name;
        this.getDescription = getDescription;
        this.acceptCase = Map.copyOf(acceptCase);
        this.getDenyCase = Map.copyOf(getDenyCase);
    }
}
