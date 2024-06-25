package gymlife.model.encounter;

import gymlife.model.statistics.StatsType;
import java.util.Map;

/**
 * Class that represent a random encounter made from the player in the game.
 *
 * @param name        the name of the encounter
 * @param description the getDescription of the encounter
 * @param acceptCase  the map of stats type and corresponding values for accepting the encounter
 * @param denyCase    the map of stats type and corresponding values for denying the encounter
 */
public record Encounter(String name, String description, Map<StatsType, Integer> acceptCase,
                        Map<StatsType, Integer> denyCase) {
    /**
     * Constructs an Encounters object with the given getDescription, accept case, and deny case.
     *
     * @param name        the name of the encounter
     * @param description the getDescription of the encounter
     * @param acceptCase  the map of stats type and corresponding values for accepting the encounter
     * @param denyCase    the map of stats type and corresponding values for denying the encounter
     */
    public Encounter(final String name, final String description, final Map<StatsType, Integer> acceptCase,
                     final Map<StatsType, Integer> denyCase) {
        this.name = name;
        this.description = description;
        this.acceptCase = Map.copyOf(acceptCase);
        this.denyCase = Map.copyOf(denyCase);
    }
}
