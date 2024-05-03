package gymlife.model;

import java.util.Map;
import java.util.Optional;

import gymlife.model.api.EncountersManager;
import gymlife.utility.StatsType;

/**
 * Implementation of the EncountersManager interface.
 */
public class EncountersManagerImpl implements EncountersManager{
    private Optional<Encounters> encounter;

    /**
     * Retrieves a random encounter .
     * 
     * @return The random encounter.
     */
    @Override
    public Optional<Encounters> getEncounter() {
        this.encounter =  Encounters.getRandomEncounter();
        return encounter;
    }

    /**
     * Retrieves the map of accept cases for the current encounter.
     * 
     * @return The map of accept cases.
     */
    @Override
    public Map<StatsType, Integer> getAcceptMap() {
        return encounter.get().getAcceptCase();
    }

    /**
     * Retrieves the map of deny cases for the current encounter.
     * 
     * @return The map of deny cases.
     */
    @Override
    public Map<StatsType, Integer> getDenyMap() {
        return encounter.get().getDenyCase();
    }
}
