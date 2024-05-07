package gymlife.model.encounter;

import gymlife.model.statistics.StatsType;

import java.util.Map;

public class Encounter {
    private String description;
    private Map<StatsType, Integer> acceptCase;
    private Map<StatsType, Integer> denyCase;

    /**
     * Constructs an Encounters object with the given description, accept case, and deny case.
     *
     * @param description the description of the encounter
     * @param acceptCase the map of stats type and corresponding values for accepting the encounter
     * @param denyCase the map of stats type and corresponding values for denying the encounter
     */
    public Encounter(final String description, final Map<StatsType, Integer> acceptCase,
                     final Map<StatsType, Integer> denyCase) {
        this.description = description;
        this.acceptCase = acceptCase;
        this.denyCase = denyCase;
    }
    /**
     * Returns the description of the encounter.
     *
     * @return the description of the encounter
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the map of stats type and corresponding values for accepting the encounter.
     *
     * @return the map of stats type and corresponding values for accepting the encounter
     */
    public Map<StatsType, Integer> getAcceptCase() {
        return Map.copyOf(acceptCase);
    }
    /**
     * Returns the map of stats type and corresponding values for denying the encounter.
     *
     * @return the map of stats type and corresponding values for denying the encounter
     */
    public Map<StatsType, Integer> getDenyCase() {
        return Map.copyOf(denyCase);
    }
}
