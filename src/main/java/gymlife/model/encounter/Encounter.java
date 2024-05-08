package gymlife.model.encounter;

import gymlife.model.statistics.StatsType;

import java.util.Map;
/**
 * Class that represent a random encounter made from the player in the game.
 */
public class Encounter {
    private  final String name;
    private  final String description;
    private  final Map<StatsType, Integer> acceptCase;
    private  final Map<StatsType, Integer> denyCase;
    /**
     * Constructs an Encounters object with the given description, accept case, and deny case.
     * @param name the name of the encounter
     * @param description the description of the encounter
     * @param acceptCase  the map of stats type and corresponding values for accepting the encounter
     * @param denyCase    the map of stats type and corresponding values for denying the encounter
     */
    public Encounter(final String name, final String description, final  Map<StatsType, Integer> acceptCase,
                     final Map<StatsType, Integer> denyCase) {
        this.name = name;
        this.description = description;
        this.acceptCase = Map.copyOf(acceptCase);
        this.denyCase = Map.copyOf(denyCase);
    }
    /**
     * Returns the description of the encounter.
     *
     * @return the description of the encounter
     */
    public String description() {
        return description;
    }
    /**
     * Returns the map of stats type and corresponding values for accepting the encounter.
     *
     * @return the map of stats type and corresponding values for accepting the encounter
     */
    public Map<StatsType, Integer> acceptCase() {
        return Map.copyOf(acceptCase);
    }
    /**
     * Returns the map of stats type and corresponding values for denying the encounter.
     *
     * @return the map of stats type and corresponding values for denying the encounter
     */
    public Map<StatsType, Integer> denyCase() {
        return Map.copyOf(denyCase);
    }
    /**
     * Returns the map of stats type and corresponding values for denying the encounter.
     *
     * @return the map of stats type and corresponding values for denying the encounter
     */
    public String name() {
        return name;
    }
}
