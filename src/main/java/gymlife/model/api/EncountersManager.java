package gymlife.model.api;

import java.util.Map;
import java.util.Optional;

import gymlife.model.encounter.Encounters;
import gymlife.model.statistics.StatsType;

/**
 * The EncountersManager interface represents a manager for gym encounters.
 * It provides methods to retrieve encounters and their associated values.
 */
public interface EncountersManager {
    /**
     * Return a casual Encounter encounter.
     * 
     * @return the Encounters object representing the encounter
     */
    Optional<Encounters> getEncounter();
    /**
     * Return the accept values for the encounter.
     * 
     * @return a Map containing the StatsType and their corresponding accept values
     */
    Map<StatsType, Integer> getAcceptMap();
    /**
     * Return the deny values for the encounter.
     * 
     * @return a Map containing the StatsType and their corresponding deny values
     */
    Map<StatsType, Integer> getDenyMap();
}
