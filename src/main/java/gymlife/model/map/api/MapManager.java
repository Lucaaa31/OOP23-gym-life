package gymlife.model.map.api;

import gymlife.model.encounter.Encounter;

import java.util.Optional;

/**
 * MapManager is a class that maintains the current map of the game. it has the necessary methods to change maps.
 */
public interface MapManager {
    /**
     * Method that changes the map.
     * @param newMap The new map to replace to current one.
     * @return optional of an encounter if an encounter occurs, empty otherwise.
     */
    Optional<Encounter> changeMap(GameMap newMap);

    /**
     * Method to return the current map.
     * @return Returns the current Map.
     */
    GameMap getCurrentMap();
}
